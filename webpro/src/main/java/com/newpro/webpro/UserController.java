package com.newpro.webpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
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
        return service.getUserById(id);
        //return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public User PostMethodName (@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping("/")
    public User PutMapping (@RequestBody User newUser){
        return service.userUpdate(newUser);
    }

    @DeleteMapping("/{id}")
    public String DeleteUser(@PathVariable String id){
       return service.deleteUser(id);
    }
}
