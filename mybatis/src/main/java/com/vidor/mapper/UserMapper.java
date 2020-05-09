package com.vidor.mapper;

import com.vidor.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
public interface UserMapper {

//    @Select("select * from user")
    List<User> getUserList();

    User getAUser(int id);

    int addAUser(User user);

    void deleteUser(int id);

    List<User> search(User user);
}
