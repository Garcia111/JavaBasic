package spring.ioc;


import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 1.首先类需要是一个虚拟类
 * 2.其中需要由一个需要注入原型bean的get方法，并用@Lookup进行修饰
 * @author chengxiao
 */
@Service("indexService2")
@Scope("singleton")
public abstract class IndexServiceImpl2 implements IndexService{

    //使用lookup注解相当于将原有的依赖注入的注入方式改为了依赖寻找
    //使用原有的@Autowired注解注入，在一个单例bean中注入的原型bean，标注为原型失去了原有的意义，
    //但是使用@Lookup注解，则会查看bean的作用域，如果bean声明为原型,则每次都注入一个新的bean
    @Lookup
    public abstract IndexDao getIndexDao();

    @Override
    public void service() {
        System.out.println("service:"+this.hashCode());
        System.out.println("dao:"+getIndexDao().hashCode());
    }
}
