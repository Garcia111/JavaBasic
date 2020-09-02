package spring.aop.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import spring.annotation.pointcut;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
@Repository("indexDao")
@Scope("prototype")
public class IndexDao implements Dao{

    @Override
    public void query1(){
        System.out.println("query1");

    }


    @pointcut
    @Override
    public void query2(){
        System.out.println("query2");

    }

    @Override
    public void queryWithArg1(String s){
        System.out.println("query with arg1");
        System.out.println(s);

    }


    @Override
    public void queryWithArg2(Integer i){
        System.out.println("query with arg2");

    }
}
