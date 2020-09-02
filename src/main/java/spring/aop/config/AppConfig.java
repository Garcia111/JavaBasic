package spring.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author：Cheng.
 * @since： 2020/08/05
 */
@Configuration
//Spring默认使用JDK动态代理，proxyTargetClass = false的时候，使用的是jdk动态代理
//proxyTargetClass = true的时候，使用的是cglib动态代理
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(value = {"spring.aop"})
public class AppConfig {
}
