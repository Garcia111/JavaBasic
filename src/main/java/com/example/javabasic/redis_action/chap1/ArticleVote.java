package com.example.javabasic.redis_action.chap1;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

/**
 * @author：Cheng.
 * @date：Created in 17:47 2019/11/28
 */
public class ArticleVote {

    private static final int ONE_WEEK_SECONDS=7*84000;
    private static final int VOTE_SCORE=432;

    private static final int ARTICLES_PER_PAGE=25;

    @Autowired
    private Jedis jedis;


    /**
     * 发布一篇新的文章
     */
    public void articlePost(Article article,String voter){

        //字符串：article的数量  key:article_counr
        //hash表：存储文章实体 key:article_文章id
        //集合：文章的投票人（需要设置过期时间，1周，文章发布时间超过一周将不能再投票）
        //有序集合1.有序集合根据分数；2.有序集合根据发布时间


        //1.获取当前文章的数量+1,作为当前文章的索引号
        Long currentArticleId = jedis.incr("article_count");
        article.setId("article_"+currentArticleId);

        //2.文章投票集合,
        jedis.sadd("vote_"+article.getId(),voter);
        jedis.expire(article.getId(),ONE_WEEK_SECONDS);

        //3.文章数据hash表，key:article_文章索引号
        HashMap<String,String> articleData = new HashMap<>();
        articleData.put("id",article.getId());
        articleData.put("title",article.getTitle());
        articleData.put("link",article.getLink());
        articleData.put("author",article.getAuthor());
        articleData.put("publishTime",article.getPublishTime().toString());
        articleData.put("votes","0");
        jedis.hmset(article.getId(),articleData);

        //文章按照分数排序的有序集合
        long now = System.currentTimeMillis() / 1000;
        jedis.zadd("score_order_zset",now+VOTE_SCORE,article.getId());

        //文章按照发布时间排序的有序集合
        jedis.zadd("publish_time_zset",
                article.getPublishTime().getLong(MILLI_OF_SECOND),
                article.getId());

    }




    public void articleVote(Article article,String voter){

        long cutoff = (System.currentTimeMillis()/1000)-ONE_WEEK_SECONDS;
        String publishTime = jedis.hget(article.getId(),"publishTime");
        if(Long.valueOf(publishTime)<cutoff){
            //如果文章的发布时间早于一周以前，则不能再继续进行投票
            System.out.println("文章的发布时间早于一周以前，不能再继续进行投票");
            return;
        }else{
            //下面这两个步骤应该是处于事务中，具有原子性,使用pipeline一次提交多个命令，使用multi开启事务
            //1.文章的hash表，文章的投票次数+1；
            //2.zset 文章的投票分数有序集合，分数+VOTE_SCORE
            //3.文章的投票人集合中增加当前投票人
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            pipeline.hincrBy(article.getId(),"votes",1);
            pipeline.sadd("vote_"+article.getId(),voter);
            pipeline.zincrby("score_order_zset",VOTE_SCORE,article.getId());

            pipeline.exec();
            pipeline.sync();
        }


    }



    public List<Map<String,String>> getArticlesByPage(int page, String orderZsetKey){
        return null;
    }


}



@ToString
@EqualsAndHashCode
@Accessors(chain = true)
class Article{

    private String id;

    private String title;

    private String link;

    private String author;

    private LocalDateTime publishTime;

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

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime pushlishTime) {
        this.publishTime = pushlishTime;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
