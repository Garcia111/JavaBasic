package com.example.javabasic.jwt_session_cookie.cookie;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author：Cheng.
 * @date：Created in 18:19 2019/11/16
 */
public class CookieTest extends HttpServlet {


    /**
     * 创建和设置cookie
     * @param request
     * @param response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        String name = request.getParameter("username");
        Cookie cookie = new Cookie("username",name);
        //设置cookie在客户端上存活30分钟
        cookie.setMaxAge(30*60);
        //将此cookie增加为“Set-Cookie”响应首部
        response.addCookie(cookie);
    }

    /**
     * servelet取得cookie
     * @param request
     * @param response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i=0;i<cookies.length;i++){
                Cookie cookie = cookies[i];
                if(cookie.getName().equals("username")){
                    String username = cookie.getValue();
                    System.out.println("Hello "+username);
                    break;
                }
            }
        }


    }

}
