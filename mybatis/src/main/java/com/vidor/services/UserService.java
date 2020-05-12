package com.vidor.services;

import com.vidor.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getAUser(int id);
    int addAUser(User user);
    void deleteAUser(int id);
    List<User> search(User user);
    List<User> searchByRange(User user);
    void updateUser(User user);
}
