package spring.aop.dao;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
public interface Dao {

    public void query1();


    public void query2();


    public void queryWithArg1(String s);


    public void queryWithArg2(Integer i);

}
