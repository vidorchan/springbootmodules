package com.vidor.controller;

import com.vidor.pojo.User;
import com.vidor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAllUsers")
    public List<User> getAll(){
        System.out.println("---------------");
        List<User> u = userService.getUserList();
        System.out.println(u.get(0).getRoles().toString());
        return u;
    }

    @PostMapping("insertAUser")
    public void insertAUser(@RequestBody User user){
        userService.addAUser(user);
    }

    @PostMapping("insertAUserAndReturnId")
    public int insertAUserAndReturnId(@RequestBody User user){
        System.out.println(user.getUserName());
        if(1 == userService.addAUser(user)){
            return user.getUserId();
        }
        return 0;
    }

    @GetMapping("getAUser/{ID}")
    public User insertAUser(@PathVariable("ID") int id){
        return userService.getAUser(id);
    }

    @DeleteMapping("deleteAUser/{id}")
    public void deleteAUser(@PathVariable int id){
        userService.deleteAUser(id);
    }

//    @PostMapping("search")
//    public List<User> search(@RequestBody User user){
//        return userService.search(user);
//    }

}
