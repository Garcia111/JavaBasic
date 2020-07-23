package com.example.javabasic.shiro.factory;

import java.util.LinkedHashMap;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        //可以通过数据库设计，从数据表中配置访问页面的权限，避免了页面访问的死设置
        return map;
    }

}
