package spring.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
@Aspect
@Component
public class AspectJ {


    @Pointcut("execution(* spring.aop.dao.*.*(..))")
    public void pointCutExecution(){}


    @Pointcut("within(spring.aop.dao.IndexDao)")
    public void pointCutWithin(){}


    @Pointcut("args(java.lang.String) && args(java.lang.Integer)")
    public void pointCutWithArg(){}


    //意思为 spring.aop.dao 包下的所有类中的方法 除去只有一个String类型的参数的方法，其他的方法都作为连接点
    @Pointcut("pointCutExecution() && ! pointCutWithArg()")
    public void pointCutCompose(){}

    //this是指最终的代理对象，但是不能使用jdk动态代理，需要使用cglib动态代理
    @Pointcut("this(spring.aop.dao.IndexDao)")
    public void pointWithThis(){}

    //target是指原被代理的对象,使用此种类型的切点，使用jdk动态代理也是可以实现织入的
    @Pointcut("target(spring.aop.dao.IndexDao)")
    public void pointWithTarget(){}

    //只对使用了 @pointcut注解修饰的方法进行增强
    @Pointcut("@annotation(spring.annotation.pointcut)")
    public void pointWithAnnotation(){}


    @Before("pointWithAnnotation()")
    public void before(){
        System.out.println("before");
    }




}
