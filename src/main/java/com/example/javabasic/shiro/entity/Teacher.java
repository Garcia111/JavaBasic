package com.example.javabasic.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public
class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    //教师编号 （自增长）
    private Integer tid;
    //教师号
    private String teacherId;
    //真实姓名
    private String name;
    //密码
	private String password;
	//性别
    private Integer sex;
    //创建时间
    private Date createTime;
    //状态 1：正常 2：冻结
    private Integer isAvalible;
    //联系方式
    private String mobile;
    //email
    private String email;
    //头像
    private String picImg;



}
