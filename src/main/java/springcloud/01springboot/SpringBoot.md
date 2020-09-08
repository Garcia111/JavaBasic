1.POM依赖
  1）spring-boot-starter-parent:每个springboot项目必须依赖的
  2）spring-boot-starter-web:web依赖
  3）spring-boot-maven-plugin:
      springboot内置了tomcat，不再将程序打包成war包，而是打包成jar包。maven插件就是将程序打包成jar包的工具。

2.application.properties
    1)debug = true 开启调试模式
    2）spring.banner.charset = UTF-8 设置字符串编码
    3）spring.thymleaf.cache = false
       关闭缓存，这样服务启动之后，如果变更了静态的html文件，不用重启服务即可生效
    4）如果在路径中直接传一个Date参数2020-09-08，Spring是只能将改参数理解为一个普通的字符串，无法将其解析为一个日期。
       如何实现传递2020-09-08，后台自动将其解析为一个Date参数呢？
       在application.properties中新增下面三个配置
       1>spring.mvc.date-formate = yyyy-MM-dd:设置web的日期格式
       2>spring.jackson.date-format = yyyy-MM-dd:设置json中的日期格式
       3>spring.jackson.time-zone:GMT+8: 设置时区为格林尼治时间东八区时间
    5）配置日志信息
       spring默认的日志配置：
            logging.file:配置日志输出文件地址
            logging.level.【路径】:配置具体路径下日志的输出级别
            专门做日志的框架：log4j log4j2 logback

