Spring @Transactional 失效和传播行为
 @Transactional注解的几个参数：
 1.propagation:控制事务传播
 2.isolation：控制事务隔离级别，默认与数据库的默认隔离级别相同
 3.readOnly:控制事务是可读写还是只是可读，默认可读写
 4.timeout:控制事务的超时时间，单位秒，默认与数据库的事务控制系统相同。
 5.rollbackFor= RuntimeException.class:控制事务遇到哪些异常才会回滚，默认是RuntimeException
 6.rollbackForClassName = RuntimeException 同上
 7.noRollbackFor = NullPointerException.class:控制事务遇到哪些异常不会回滚
 8.noRollbackForClassName = NullPointerException
 
 
propagation属性：
    事务传播是指：f1()有事务X，f2()有事务Y，当A.f1()调用B.f2()的时候，B.f2()中的代码执行哪个事务。 
    如果B.f2()方法上的@Transaction注解propagation属性的取值为下值 
    (1)Propagation.NEVER
        B.f2()不能在任何事务下执行，如果A.f1()调用它，那么抛出异常。
    （2）Propagation.NOT_SUPPORTED
        B.f2()不需要在任何事务中执行，如果A.f1()调用B.f2(),那么事务X被挂起，B.f2()执行完毕之后才会恢复。
    （3）Propagation.REQUIRES_NEW
        如果A.f1()调用B.f2(),那么事务X被挂起，重新创建一个事务Y，B.f2()在事务Y中执行，Y执行完毕再继续X事务。
        
        事务回滚：
        因为B.f2()是新起一个事务，那么就是存在两个不同的事务。如果B.f2()方法已经提交，那么A.f1()失败回滚，B.f2()是不会回滚的。
        如果B.f2()失败回滚，如果它抛出的异常被A.f1()捕获，A.f1()中的事务仍然要提交。
        
   （4）Propation.SUPPORTS
        如果B.f2()被f1()调用，那么执行X事务，如果被没有事务的方法调用，那么就在没有事务的环境下执行，
   （5）Propagation.REQUIRED----默认值
        如果A.f1()调用B.f2()，那么B.f2()在事务X中执行；如果B.f2()被一个没有在事务中的方法调用，那么就自己新起一个事务Y。
        
        回滚：
        如果A.f1()或者B.f2()内部的任何地方出现异常，事务都会被回滚，即使B.f2()的事务已经被提交，但是在接下来的A.f1()方法中出现了异常
        B.f1()也会回滚。
        
        
   （6）Propagation.MANDATORY:
        B.f2()不能开启自己的事务，只能被开启了事务A.f1()调用，如果没有被开启事务其他的方法调用，则抛出异常。
        
   （7）Propagation.NESTED
            NESTED与REQUIRES_NEW的区别是，NESTED的事务与他的事务是相依的，他的提交是要等他的父事务一起提交，也就是说如果父事务最后
            回滚，它也是要回滚的. NESTED事务的好处是他有一个savepoint。
            
            
            
   事务失效的几种可能：
         
         1.标注事务的方法不是public的；
         2.你的异常类型不是unchecked异常
            如果我想check异常也想回滚怎么办，需要在注解上面写明异常类型即可。类似的还有noRollbackFor，自定义不会回滚的异常
         3.数据库引擎要支持事务，比如MySQL数据库的myisam事务是不起作用的。
         4.检查spring是否扫描到你这个包
         6.检查是不是同一个类中的方法调用
            如果一个类中的a方法调用了同一个类中的b方法，那么会导致事务无效？？？、-----待验证
         7.UNCHECKED异常是不是被CATCH住了
         
   为什么同一个类中的方法调用会导致事务无效呢？
         
     push_result表存储推送结果，无论推送成功或者失败都会存储
     mdn表只有在推送成功的情况下才会存储值
     
     
     1.存储mdn表，推送成功，存入值；推送失败，mdn表回退，不存入值，push_result表不回退
     2.push_result存储值根据推送结果存储值
         
         
         
         
         
         
         
         
                                                    
    

2.事务失效
