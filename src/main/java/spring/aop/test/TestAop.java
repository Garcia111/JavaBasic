package spring.aop.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.config.AppConfig;
import spring.aop.dao.Dao;
import spring.aop.dao.IndexDao;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
public class TestAop {

    public static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
//        IndexDao dao = (IndexDao) annotationConfigApplicationContext.getBean(IndexDao.class);


        Dao dao = (Dao) annotationConfigApplicationContext.getBean("indexDao");
        System.out.println( dao instanceof  IndexDao);
        dao.query1();
        dao.query2();
        dao.queryWithArg1("s");
        dao.queryWithArg2(1);

        Class<?>[] interfaces = new Class[]{Dao.class};
        //获取ProxyDao的反编译文件
        byte[] bytes = ProxyGenerator.generateProxyClass("ProxyDao",interfaces);
        File file = new File("F:\\byte.class");

        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
