package com.example.javabasic.redis_action.chap1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ZParams;

import java.util.*;

public class charpter1 {
    private static final int ONE_WEEK_SECONDS=7*84000;
    private static final int VOTE_SCORE=432;

    private static final int ARTICLES_PER_PAGE=25;
    public static  void main(String[] args){
        new charpter1().run();
    }

    public void run(){
        Jedis conn = new Jedis("localhost");
        //Redis的SELECT 命令用于切换到指定的数据库，数据库索引号index用数字指定，以0作为起始索引值
        conn.select(15);

        String articleId = post_article(
                conn, "李四", "新文章", "http://www.google.com");


        System.out.println("We posted a new article with id: " + articleId);
        System.out.println("Its HASH looks like:");
        Map<String,String> articleData = conn.hgetAll("article:" + articleId);
        for (Map.Entry<String,String> entry : articleData.entrySet()){
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println();

        //对文章进行投票
        article_vote(conn, "张三", "article:" + articleId);
        //获取文章的投票总数量
        String votes = conn.hget("article:" + articleId, "votes");
        System.out.println("We voted for the article, it now has votes: " + votes);
        assert Integer.parseInt(votes) > 1;

        System.out.println("The currently highest-scoring articles are:");
        //所有文章的hash表数据
        List<Map<String,String>> articles = getArticles(conn, 1);
        printArticles(articles);
        assert articles.size() >= 1;

        addGroups(conn, articleId, new String[]{"new-group"});
        System.out.println("We added the article to a new group, other articles include:");
        articles = getGroupArticles(conn, "new-group", 1);
        printArticles(articles);
        assert articles.size() >= 1;

    }



    private void printArticles(List<Map<String,String>> articles){
        for (Map<String,String> article : articles){
            System.out.println("  id: " + article.get("id"));
            for (Map.Entry<String,String> entry : article.entrySet()){
                if (entry.getKey().equals("id")){
                    continue;
                }
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }


    /**
     * 新发布一篇文章
     * @param conn
     * @param user
     * @param title
     * @param link
     * @return
     */
    public String post_article(Jedis conn, String user, String title, String link){

        //文章数量+1，文章总数：字符串类型，key- article:  value-文章总数
        String articleId = String.valueOf(conn.incr("article:"));

        //存储文章的投票人
        //文章投票人：集合类型 key-voted:文章的索引id  value-投票人名字
        String voted = "voted:" + articleId;
        conn.sadd(voted, user);
        //设置投票的过期时间，文章超过一周之后将不能再继续投票
        conn.expire(voted, ONE_WEEK_SECONDS);

        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        //存储文章hash对象，类型HASH表  key-article:文章索引 value-文章的各个属性
        conn.hmset(article, articleData);

        //存储当前文章的投票得分，类型有序集合 key-socre: score-当前文章的分数 member:article:文章索引
        conn.zadd("score:", now + VOTE_SCORE, article);

        //存储当前文章的发布时间,类型有序集合， key-time: score-当前时间 member-article:文章索引
        conn.zadd("time:", now, article);

        //存储以上两个有序集合，使得文章既可以按照得分排序，也可以按照发布时间排序
        return articleId;
    }


    /**
     * 对文章进行投票
     * @param conn
     * @param user
     * @param arcticle
     */
    public void article_vote(Jedis conn, String user, String arcticle){
        long cutoff=(System.currentTimeMillis()/1000)-ONE_WEEK_SECONDS;
        //如果文章的发布时间为一周以前，则不能再对该文章进行投票
        if(conn.zscore("time:",arcticle)<cutoff){
            return;
        }else{
            //文章的投票人集合里面添加当前投票人
            String article_id=arcticle.substring(arcticle.indexOf(":")+1);
            Pipeline pipeline= conn.pipelined();
            pipeline.multi();
            pipeline.sadd("voted:"+article_id,user);
            Response<List<Object>> listResponsePipeline= pipeline.exec();//执行批量语句
            //同步管道？
            pipeline.sync();
//         for(Object object:listResponsePipeline.get()){
//             System.out.println(object);
//         }

            //给文章增加投票分数
            if((Long)listResponsePipeline.get().get(0)==1) {
//          Response<List<Object>> responseFromPipeLine=pipeline.exec();
//          System.out.println(responseFromPipeLine.get().get(0));
                pipeline.multi();
                //有序集合中，此文章的投票得分要加上相应得分
                pipeline.zincrby("score:" + article_id, VOTE_SCORE, arcticle);
                //文章的hash集合里面的票数也要+1
                pipeline.hincrBy(arcticle, "votes", 1);
            }
            pipeline.exec();
            pipeline.sync();//close the pipeline
        }

    }


    public List<Map<String,String>> getArticles(Jedis conn, int page){
         return getArticles(conn,page,"score:");
    }

    /**
     * 按某种排序取得第几页的文章列表
     * @param conn
     * @param page
     * @param order
     * @return
     */
    public List<Map<String,String>> getArticles(Jedis conn, int page, String order){
        //start即offset+1
        int start=(page-1)*ARTICLES_PER_PAGE;
        //end 即offset+limit
        int end=start+ARTICLES_PER_PAGE-1;
        //按照排序方式倒叙排序，返回的是member列表,而不是score
        Set<String> ids=conn.zrevrange(order,start,end);

        List<Map<String,String>> articles=new ArrayList<>();
        String[] idstring= ids.toArray(new String[ids.size()]);
        Pipeline pipeline=conn.pipelined();//事务开始
        pipeline.multi();
        for(String id:ids){
               pipeline.hgetAll(id);
//            Map<String,String> artcle=conn.hgetAll(id);
//            artcle.put("id",id);
//            articles.add(artcle);
        }
        Response<List<Object>> listResponsePipeLine=pipeline.exec();
        pipeline.sync();
        for(int i=0;i<listResponsePipeLine.get().size();i++){
            //某个文章的所有hash表
            Map<String,String> article= (Map<String, String>) listResponsePipeLine.get().get(i);
            article.put("id",idstring[i]);
            articles.add(article);
        }
//        for(Object object:listResponsePipeLine.get()){
//             System.out.println(object);
//             Map<String,String> article= (Map<String, String>) object;
//             articles.add(article);
//         }
        return articles;
    }



    public void addGroups(Jedis conn, String articleId, String[] toAdd){
        String article="article:"+articleId;
        for(String group :toAdd){
            conn.sadd("group:"+group,article);
        }
    }
    public List<Map<String,String>> getGroupArticles(Jedis conn, String group, int page) {
        return getGroupArticles(conn, group, page, "score:");
    }
    public List<Map<String,String>> getGroupArticles(Jedis conn, String group, int page, String order) {
        String key = order + group;
        if (!conn.exists(key)) {
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key, params, "group:" + group, order);
            conn.expire(key, 60);
        }
        return getArticles(conn, page, key);
    }
}
