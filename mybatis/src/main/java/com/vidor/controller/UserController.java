package com.vidor.controller;

import com.fasterxml.jackson.annotation.JsonView;
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

    @PostMapping("updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }


    @GetMapping("getAUser/{ID}")
    public User insertAUser(@PathVariable("ID") int id){
        return userService.getAUser(id);
    }

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
