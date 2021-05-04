package com.newpro.webpro.service;

import com.newpro.webpro.model.User;
import com.newpro.webpro.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUserByName(String Name){
        List<User> users = userRepository.findByName(Name);
        System.out.println("Getting data from DB : " + users);
        return users;
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Getting data from DB : " + users);
        return users;
    }
        public String deleteUser(String id) {
        userRepository.deleteById(id);
        return id;
    }

    public User userUpdate(User newUser) {
        User oldUser=userRepository.findById(newUser.getId()).orElse(null);
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        userRepository.save(oldUser);
        return oldUser;
    }
}
