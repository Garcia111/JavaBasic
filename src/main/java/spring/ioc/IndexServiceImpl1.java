package spring.ioc;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 使用实现ApplicationContextAware的方式来实现在单例的bean中使用原型的bean
 * 这样做的缺点是增加了代码的耦合性
 * @author chengxiao
 */
@Service("indexService1")
@Scope("singleton")
public class IndexServiceImpl1 implements IndexService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void service() {
        System.out.println("service:"+this.hashCode());
        //通过applicationContext的方式获取dao
        IndexDao indexDao = (IndexDao) applicationContext.getBean("dao");
        System.out.println("dao:"+indexDao.hashCode());
    }

}
