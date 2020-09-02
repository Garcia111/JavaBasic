package spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.aop.dao.Dao;
import spring.aop.dao.IndexDao;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
@Aspect("perthis(this(spring.aop.dao.IndexDao))")
@Component
@Scope("prototype")
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

    @DeclareParents(value = "spring.aop.dao.*+",defaultImpl = IndexDao.class)
    public static Dao dao;



//    @Before("pointWithAnnotation()")
//    public void before(JoinPoint joinPoint){
//        System.out.println("before");
//        System.out.println(joinPoint.getThis());
//        System.out.println(joinPoint.getTarget());
//    }


    @Around("pointCutWithin()")
    public void Around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("b");

        System.out.println("切面hashCode:"+proceedingJoinPoint.getThis().hashCode());
//        //getDeclaringTypeName（）获得方法所在的类名
//        String signatureDeclareType = proceedingJoinPoint.getSignature().getDeclaringTypeName();
//        System.out.println("signatureDeclareType:"+signatureDeclareType);
//
//        //getSignature().getName() 获得连接点方法的方法名
//        String signatureName = proceedingJoinPoint.getSignature().getName();
//        System.out.println("signatureName:"+signatureName);

        Object[] args = proceedingJoinPoint.getArgs();
        if(args != null && args.length >0){
            for (int i = 0; i < args.length; i++) {
                if(args[i] instanceof java.lang.String){
                    args[i] += " hello! ";
                }
            }
        }

        try {
            proceedingJoinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("a");
        System.out.println("==========");
    }



}
