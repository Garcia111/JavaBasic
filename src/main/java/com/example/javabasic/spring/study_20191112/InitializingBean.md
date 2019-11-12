InitializingBean接口

    InitializingBean接口为bean提供了初始化方法的方式，它只是包括afterPropertiesSet方法。
    凡是继承了该接口的类，在初始化bean的时候都会执行该方法。
    
    
    那么在配置bean的时候，使用init-method配置也可以为bean配置初始化方法，哪个会先执行呢？
    