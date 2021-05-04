package com.newpro.webpro;

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
        return userRepository.findByName(Name);
    }
    public User getUserById(String Id){
        return userRepository.findById(Id).orElse(null);
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
