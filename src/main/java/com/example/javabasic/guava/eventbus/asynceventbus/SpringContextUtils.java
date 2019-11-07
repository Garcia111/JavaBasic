package com.example.javabasic.guava.eventbus.asynceventbus;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author：Cheng.
 * @date：Created in 15:57 2019/11/7
 */
@Component(value = "mySpringContextUtils")
public class SpringContextUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringContextUtils.beanFactory = configurableListableBeanFactory;
    }

      public  <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    public  <T> T getBean(Class<T> clz) throws BeansException {
        T result = beanFactory.getBean(clz);
        return result;
    }

    public  <T> List<T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type).entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }


    public  List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> beansWithAnnotation =
                beanFactory.getBeansWithAnnotation(annotationType);

        // 将 map 的 value 收集起来到一个 list 中
        return beansWithAnnotation.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
