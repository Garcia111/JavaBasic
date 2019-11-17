package com.example.javabasic.jwt_session_cookie.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author：Cheng.
 * @date：Created in 20:05 2019/11/16
 */
public class HttpSessionListenerTest implements HttpSessionListener {
    //HttpSessionListener---允许你跟踪这个Web应用中活动会话的个数

    static private int activeSessions;

    public static int getActiveSessions(){
        return activeSessions;
    }



    @Override
    public void sessionCreated(HttpSessionEvent event){
        activeSessions++;
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        activeSessions--;
    }
}
