package com.example.javabasic.jwt.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.websocket.Session;

/**
 * @author：Cheng.
 * @date：Created in 20:11 2019/11/16
 */
public class HttpSessionAttributeListenerTest implements HttpSessionAttributeListener {

    //利用这个监听者，每一次向会话增加属性 删除属性或者替换属性都能跟踪到


    /**
     * 监听属性被创建
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event){
        String name = event.getName();//新添加属性的名称
        Object value = event.getValue();//新添加属性的value
        HttpSession session = event.getSession();
        System.out.println("session:"+session.getId()+"Attribute added:"+name+":"+value);
    }


    /**
     * 监听属性被删除
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event){
        String name = event.getName();
        Object value = event.getValue();
        System.out.println("Attribute removed:"+name+":"+value);
    }


    /**
     * 监听属性被修改
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event){
        String name = event.getName();
        Object value = event.getValue();
        System.out.println("Attribute replaced:"+name+":"+value);
    }



}
