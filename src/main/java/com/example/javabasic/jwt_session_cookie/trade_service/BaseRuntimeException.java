package com.example.javabasic.jwt_session_cookie.trade_service;



public class BaseRuntimeException extends Exception{

    private String message;

    private Throwable cause;
    public BaseRuntimeException(String message){
        this.message = message;
    }


    public BaseRuntimeException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
