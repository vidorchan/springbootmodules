package com.vidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vidor.pojo.User;
import com.vidor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Resouce:根据名称进行匹配，然后进行类型匹配
     * Qualifer：根据名称匹配，需要和Autowired一起用，否则报NULL pointer异常
     */
    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("getAllUsers")
    public List<User> getAll(){
        System.out.println("---------------");
        List<User> u = userService.getUserList();
        return u;
    }

    @PostMapping("insertAUser")
    public void insertAUser(@RequestBody User user){
        userService.addAUser(user);
    }

    @PostMapping("insertAUserAndReturnId")
    public int insertAUserAndReturnId(@RequestBody User user){
        if(1 == userService.addAUser(user)){
            return user.getUserId();
        }
        return 0;
    }

    @CacheEvict(value = "user", key = "#user.userId")
    @PostMapping("updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @Cacheable(value = "user",key = "#id")
    @GetMapping("getAUser/{ID}")
    public User getAUser(@PathVariable("ID") int id){
        System.out.println("start...");
        User u = (User) redisTemplate.opsForValue().get("userId-select=" + id);
        if(u == null){
            synchronized (this){
                if(u == null){
                    u = userService.getAUser(id);
                    redisTemplate.opsForValue().set("userId-select=" + id, u);
                    System.out.println("从数据库里读取");
                } else {
                    System.out.println("从redis缓存里读取");
                }
            }
        } else {
            System.out.println("从redis缓存里读取");
        }

        return u;
    }

    @CacheEvict(value = "user", key = "#id")
    @DeleteMapping("deleteAUser/{id}")
    public void deleteAUser(@PathVariable int id){
        userService.deleteAUser(id);
    }

    @PostMapping("search")
    public List<User> search(@RequestBody User user){
        return userService.search(user);
    }

    @PostMapping("searchByRange")
    public List<User> searchUser(@RequestBody User user){
        return userService.searchByRange(user);
    }

}
