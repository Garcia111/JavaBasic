package com.example.javabasic.redis_action.chap1;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @author：Cheng.
 * @date：Created in 17:47 2019/11/28
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
@Slf4j
public class ArticleVote {

    private static final int ONE_WEEK_SECONDS = 7 * 84000;
    private static final int VOTE_SCORE = 432;

    private static final int ARTICLES_PER_PAGE = 25;

    @Autowired
    private Jedis jedis;

    @Test
    public void testArticleVote(){
        Article article = new Article();
        article.setAuthor("Bruce Eckel");
        article.setTitle("Activiti实战7");
        article.setLink("www.google.com");
        article.setPublishTime(System.currentTimeMillis());


        Article aricle = articlePost(article);
        articleVote(article, "张三");
        articleVote(article, "李四");
        articleVote(article, "王五");
        articleVote(article, "某某某");
        int page = 1;
        //start即offset+1
        int start=(page-1)*ARTICLES_PER_PAGE;
        //end 即offset+limit
        int end=start+ARTICLES_PER_PAGE-1;

        List<Map<String,String>> articleDataList = getArticlesByPage("score_order",start, end);
        printArticles(articleDataList);

        System.out.println("将文章添加到某个标签组中");
        addArticlesToGroup( article,"English");
        List<Map<String,String>> groupArticleList = getArticlesOfGroup("English",1, 10, "score_order");
        printArticles(groupArticleList);
    }

    /**
     * 发布一篇新的文章
     */
    public Article articlePost(Article article) {
        log.info("发布一篇新文章");
        //字符串：article的数量  key:article_counr
        //hash表：存储文章实体 key:article_文章id
        //集合：文章的投票人（需要设置过期时间，1周，文章发布时间超过一周将不能再投票）
        //有序集合1.有序集合根据分数；2.有序集合根据发布时间

        if(jedis.smembers("articles").contains(article.getTitle())){
            throw new RuntimeException("此文章已经发布过，不能重复发布");
        }
        jedis.sadd("articles",article.getTitle());

        //1.获取当前文章的数量+1,作为当前文章的索引号
        Long currentArticleId = jedis.incr("article_count");
        article.setId("article_" + currentArticleId);

        //2.文章投票集合,
        jedis.expire("vote_" + article.getId(), ONE_WEEK_SECONDS);

        //3.文章数据hash表，key:article_文章索引号
        HashMap<String, String> articleData = new HashMap<>();
        articleData.put("id", article.getId());
        articleData.put("title", article.getTitle());
        articleData.put("link", article.getLink());
        articleData.put("author", article.getAuthor());
        articleData.put("publishTime", article.getPublishTime().toString());
        articleData.put("votes", "0");
        jedis.hmset(article.getId(), articleData);

        //文章按照分数排序的有序集合
        long now = System.currentTimeMillis() / 1000;
        jedis.zadd("score_order", now + VOTE_SCORE, article.getId());

        //文章按照发布时间排序的有序集合
        jedis.zadd("publish_time",
                article.getPublishTime(),
                article.getId());

        return article;
    }


    public void articleVote(Article article, String voter) {
        log.info("对文章进行投票");
        long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_SECONDS;
        String publishTime = jedis.hget(article.getId(), "publishTime");
        if (Long.valueOf(publishTime) < cutoff) {
            //如果文章的发布时间早于一周以前，则不能再继续进行投票
            System.out.println("文章的发布时间早于一周以前，不能再继续进行投票");
            return;
        } else {
            if(jedis.smembers("vote_"+article.getId()).contains(voter)){
                System.out.println("此用户已经对该文章进行过投票，不能重复投票");
                return;
            }

            //下面这两个步骤应该是处于事务中，具有原子性,使用pipeline一次提交多个命令，使用multi开启事务
            //1.文章的hash表，文章的投票次数+1；
            //2.zset 文章的投票分数有序集合，分数+VOTE_SCORE
            //3.文章的投票人集合中增加当前投票人
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            pipeline.hincrBy(article.getId(), "votes", 1);
            pipeline.sadd("vote_" + article.getId(), voter);
            pipeline.zincrby("score_order", VOTE_SCORE, article.getId());

            pipeline.exec();
            pipeline.sync();
        }


    }


    public List<Map<String, String>> getArticlesByPage(String orderZsetKey, int start, int end) {
        log.info("文章数据分页查询");

        //按照某种排序倒叙排序，取相应的limit  offset   zrevrange
        Set<String> members = jedis.zrevrange(orderZsetKey, start, end);

        //根据id取得对应的文章数据 hgetAll
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        List<Map<String, String>> articleDataList = new ArrayList<>(members.size());
        members.forEach(pipeline::hgetAll);
        Response<List<Object>> articleDataRes = pipeline.exec();
        pipeline.sync();
        articleDataRes.get().stream().forEach(object -> {
            Map<String, String> articleData = (Map<String, String>) object;
            articleDataList.add(articleData);
        });
        return articleDataList;
    }


    private void printArticles(List<Map<String,String>> articles){
        for (Map<String,String> article : articles){
            for (Map.Entry<String,String> entry : article.entrySet()){
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("=======================");
        }
    }


    private Set<String> getVotersOfArticle(Article article){
        Set<String> voters = jedis.smembers("vote_"+article.getId());
        return voters;
    }


    /**
     * 将文章添加到某个分组，相当于为文章添加标签
     * 为每个群组创建一个集合，并将所有同属一个群组的文章id都记录到该集合里面
     * @param groupName
     */
    private void addArticlesToGroup(Article article,String groupName){
        if(jedis.smembers(groupName).contains(article.getId())){
            System.out.println("此群组中已经包含此文章");
            return;
        }
        jedis.sadd(groupName,article.getId());
    }

    /**
     * 将文章从某个分组中删除
     * @param article
     * @param groupName
     */
    private void removeArticleFromGroup(Article article,String groupName){
        if(!jedis.smembers(groupName).contains(article.getId())){
            System.out.println("此群组中不包含此篇文章,不能从将此文章从群组中删除");
            return;
        }
        jedis.srem(groupName,article.getId());
    }

    /**
     * 取出某个分组里面的所有文章,为了能够根据评分对群组文章进行排序和汾阳王，网站需要将同一个群组里面的所有文章
     * 都按照评分有序的存储到一个有序集合里面。
     * @param groupName
     * @return
     */
    private List<Map<String,String>> getArticlesOfGroup(String groupName,int start, int end, String orderName){
        String key = groupName+":"+orderName;

        if(!jedis.exists(key)){
            jedis.zinterstore(key,groupName,orderName);
            jedis.expire(key,600);
        }
        List<Map<String,String>> articleDataList = new ArrayList<>();
        Set<Tuple> tuples = jedis.zrevrangeWithScores(key,0,25);
        Map<String,Double> articleScores = new HashMap<>();
        tuples.stream().forEach(tuple -> {
            String element = tuple.getElement();
            Map<String,String> articleData = jedis.hgetAll(element);
            articleDataList.add(articleData);
        });

        return articleDataList;
    }
}


@ToString
@EqualsAndHashCode
@Accessors(chain = true)
class Article {

    private String id;

    private String title;

    private String link;

    private String author;

    private Long publishTime;

    private Integer votes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long pushlishTime) {
        this.publishTime = pushlishTime;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
