package com.newpro.webpro.controller;

import com.newpro.webpro.model.TodoNotes;
import com.newpro.webpro.model.User;
import com.newpro.webpro.dao.UserRepository;
import com.newpro.webpro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserInfo")
@CrossOrigin //allow all the uri to access your data
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> GetUsers(){
        return service.getUsers();
    }
    @GetMapping("/{id}")
    public User GetUser(@PathVariable String id){
        //return service.getUserById(id);
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/fullname")
    public List<User> GetUsersByFullname( @RequestParam(name= "Fullname") String Fullname){
        return service.getUsersByFullname(Fullname);
    }

    @PostMapping("/")
    public User PostMethodName (@RequestBody User user){
        return service.addUser(user);
    }

    @PostMapping("/todo")
    public TodoNotes PostTodo (@RequestBody TodoNotes todo){
        return service.addTodo(todo);
    }

    @PutMapping("/")
    public User PutMapping (@RequestBody User newUser){

        return service.userUpdate(newUser);
    }

    @DeleteMapping("/{id}")
    public String DeleteUser(@PathVariable String id){
       return service.deleteUser(id);
    }

    @GetMapping("/page")
    public Map<String , Object> GetUsersByPage(@RequestParam(name = "pageno" , defaultValue =  "0") int pageNo,
        @RequestParam(name = "pagesize" , defaultValue =  "2") int pageSize,
        @RequestParam(name = "sortby" , defaultValue =  "id") String sortBy
    ){
        return service.getUsersByPage(pageNo , pageSize,sortBy);
        //return userRepository.findById(id).orElse(null);
    }

}
