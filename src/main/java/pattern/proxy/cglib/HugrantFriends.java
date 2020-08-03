package pattern.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 休格兰特的一众好友，休格兰特类的代理类
 * @author：Cheng.
 * @since： 1.0.0
 */
@Slf4j
public class HugrantFriends implements MethodInterceptor {

    //1.首先实现MethodInterceptor类，方法调用会被转发到该类的intercept()方法

    /**
     *
     * @param o 代理类
     * @param method 拦截到的方法
     * @param args 拦截方法的参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Hugrant Friedns: Go And Get Anna come back, run for your love");
        return methodProxy.invokeSuper(o,args);
    }
}
