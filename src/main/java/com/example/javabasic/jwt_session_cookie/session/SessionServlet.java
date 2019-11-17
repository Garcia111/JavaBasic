package com.example.javabasic.jwt_session_cookie.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author：Cheng.
 * @date：Created in 10:58 2019/11/14
 */
@WebServlet("/sessionServlet")
public class SessionServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("test session attributes<br>");

        HttpSession session = request.getSession();
        if(session.isNew()){
            System.out.println("This is a new session");
        }else {
            System.out.println("welcome back!");
        }
    }
}
