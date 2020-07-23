<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
                <h4>Login Page</h4>
                <form action = "/shiro/login", method="POST" >
                    username:<input type="text" name = "username"/>
                    <br><br>

                    password:<input type="password" name = "password"/>
                    <br><br>

                    <input type = "submit" value = "submit"/>
                </form>
</body>
</html>