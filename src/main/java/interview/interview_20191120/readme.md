Spring的afterPropertiesSet方法
    1.init-method 初始化bean的时候执行，可以针对某个具体的bean进行配置。
    2. afterPropertiesSet方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。
        afterPropertiesSet 必须实现 InitializingBean接口。
        实现 InitializingBean接口必须实现afterPropertiesSet方法。
        
    3.BeanPostProcessor，针对所有Spring上下文中所有的bean，对所有的bean进行一个初始化之前和之后的代理。
      BeanPostProcessor接口中有两个方法： postProcessBeforeInitialization和postProcessAfterInitialization。 
      postProcessBeforeInitialization方法在bean初始化之前执行， 
      postProcessAfterInitialization方法在bean初始化之后执行。
   
   执行顺序：
    最先执行的是postProcessBeforeInitialization，
    然后是afterPropertiesSet，
    然后是init-method，
    然后是postProcessAfterInitialization。
    