package com.vidor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
JPA: @Transient 反序列话
JDBC： 借助Jackson工具类
序列化是指将内存中的对象状态信息转换为可以存储或传输的形式的过程 字节格式和json格式
反序列化就是存储或传输形式转为对象状态信息的过程
ObjectMapper
    String writeValueAsString(Object)//序列化
    Object readValue(json, Object.class)//反序列话
 */
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    private Integer userId;
    private String userName;
//    @JsonIgnore //加上这个之后，添加操作，数据库中存储为null
    private Integer sex;
    private Integer age;
    //数据库出来格式为"1992-02-01T16:00:00.000+0000"
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//序列化注解
    private Date dob;

    private List<Role> roles;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
