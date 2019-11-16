package com.example.javabasic.jwt.session;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * 这个监听者允许如【属性】跟踪可能会属性本身很重要的事件。
 * 属性自身可以知道，自己合适增加到session，何时从session中删除，以及会话从一个JVM迁移到另外一个JVM
 * @author：Cheng.
 * @date：Created in 20:22 2019/11/16
 */
public class Dog implements HttpSessionBindingListener,
        HttpSessionActivationListener, Serializable {


    private String breed;


    /**
     * 允许属性类知道自己何时被绑定到session
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event){

    }

    /**
     * 允许属性自己知道自己何时从一个session中解绑
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event){

    }


    /**
     * 属性知道会话何时会钝化，如果属性类未实现Serializable接口，可在此方法中使自己进行序列化
     * @param event
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent event){

    }


    /**
     * 属性知道session合适会激活，用于使序列化的属性恢复为对象字段
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent event){

    }

}
