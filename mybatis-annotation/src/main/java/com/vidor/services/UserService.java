package com.vidor.services;

import com.vidor.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getAUser(int id);
    int addAUser(User user);
    void deleteAUser(int id);
//    List<User> search(User user);
}
