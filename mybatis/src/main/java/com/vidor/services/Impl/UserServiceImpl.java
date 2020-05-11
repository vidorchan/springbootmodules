package com.vidor.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidor.mapper.RoleMapper;
import com.vidor.mapper.UserMapper;
import com.vidor.pojo.Role;
import com.vidor.pojo.User;
import com.vidor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;
//
    public List<User> getUserList(){
        List<User> users = userMapper.getUserList();
//        List<Role> roles = roleMapper.getRoleListByUserId(1);
        return users;
    }

    @Override
    public User getAUser(int id) {
        return userMapper.getAUser(id);
    }

    @Override
    public int addAUser(User user) {
        return userMapper.addAUser(user);
    }

    @Override
    public void deleteAUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> search(User user) {
        return userMapper.search(user);
    }

    @Override
    public List<User> searchByRange(User user) {
        return userMapper.searchByRange(user);
    }

}
