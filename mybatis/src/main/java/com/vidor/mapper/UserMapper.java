package com.vidor.mapper;

import com.vidor.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    User getAUser(int id);

    int addAUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    List<User> search(User user);

    List<User> searchByRange(User user);
}
