package com.example.javabasic.alibaba;

/**
 * @author：Cheng.
 * @date：Created in 10:49 2019/9/6
 */
public class GuiFan {

    //阿里巴巴Java变成规范


    //1.POJO类中的布尔类型的变量，都不要加is，否则部分框架解析会引起序列化错误；
        //反例：定义为基本数据类型Boolean isDeleted:属性，它的方法也是isDeleted()，
        // RPC框架在反向解析的时候，以为对应的属性名称是deleted，导致属性获取不到，进而抛出异常。

    //2.枚举类命名建议带上Enum后缀，枚举成员名称需要全部大写，单词用下划线隔开。
        //枚举其实就是特殊的常量类，且构造方法被默认强制是私有。
        //正例：枚举名字:DealStatusEnum，成员名称：SUCCESS/UNKNOWM_REASON.


    //3.命名规范
        //  A) Service/DAO 层方法命名规约
            //1） 获取单个对象的方法用 get 做前缀。
            //2） 获取多个对象的方法用 list 做前缀。
            //3） 获取统计值的方法用 count 做前缀。
            //4） 插入的方法用 save（推荐）或 insert 做前缀。
            //5） 删除的方法用 remove（推荐）或 dele    te 做前缀。
            //6） 修改的方法用 update 做前缀。
        //B) 领域模型命名规约
            //1） 数据对象：xxxDO，xxx 即为数据表名。
            //2） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。
            //3） 展示对象：xxxVO，xxx 一般为网页名称。
            //4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。
}
