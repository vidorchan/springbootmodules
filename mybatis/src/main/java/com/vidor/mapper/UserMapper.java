package com.vidor.mapper;

import com.vidor.pojo.User;

import java.util.List;

//开启2级缓存
//@CacheNamespace(blocking = true, implementation = EhCacheCache.class)
public interface UserMapper {

    List<User> getUserList();

    User getAUser(int id);

    int addAUser(User user);

    void deleteUser(int id);

    List<User> search(User user);

    List<User> searchByRange(User user);
}
