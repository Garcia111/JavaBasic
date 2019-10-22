package com.example.javabasic.enumration.constantenum;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author：Cheng.
 * @date：Created in 17:08 2019/9/16
 */
public enum ConstantSpecificationMethod {
    //Java的enum允许程序员为enum实例编写方法，从而为每个enum实例赋予各自不同的行为。
    //而通过常量相关的方法，每个enum实例可以具备自己独特的行为
    DATE_TIME {
        @Override
        String getInfo(){
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        @Override
        String getInfo(){
            return System.getenv("CLASSPATH");
        }
    },
    VERSION{
        @Override
        String getInfo(){
            return System.getProperty("java.version");
        }
    };
    abstract String getInfo();

    public static void main(String[] args){
        for(ConstantSpecificationMethod csm: values()){
            System.out.println(csm.getInfo());
        }
    }

}
