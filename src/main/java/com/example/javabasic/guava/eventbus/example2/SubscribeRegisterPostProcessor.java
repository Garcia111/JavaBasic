package com.example.javabasic.guava.eventbus.example2;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 10:46 2019/11/7
 */
@Service
public class SubscribeRegisterPostProcessor implements BeanPostProcessor {


    private EventBus eventBus = new EventBus();

    public void post(Object event) {
        eventBus.post(event);
    }

    @PostConstruct
    public void init() {

        // 获取所有带有 @EventBusListener 的 bean，将他们注册为监听者
        List<Object> listeners = SpringContextUtils.getBeansWithAnnotation(EventBusListener.class);
        for (Object listener : listeners) {
            eventBus.register(listener);
        }
    }




    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> subscriberClazz = bean.getClass();
        Method[] methods = subscriberClazz.getMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(Subscribe.class)){
                eventBus.register(bean);
                break;
            }
        }
        return bean;
    }

}
