package com.newpro.webpro.service;

import com.newpro.webpro.dao.TodoNotesRepository;
import com.newpro.webpro.model.TodoNotes;
import com.newpro.webpro.model.User;
import com.newpro.webpro.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public TodoNotesRepository todoNotesRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return id;
    }

    public User userUpdate(User newUser) {
        User oldUser = userRepository.findById(newUser.getId()).orElse(null);
        oldUser.setFullname(newUser.getFullname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setAge(newUser.getAge());
        oldUser.setPassword(newUser.getPassword());
        userRepository.save(oldUser);
        return oldUser;
    }

    public Map<String, Object> getUsersByPage(int pageNo, int pageSize, String sortBy) {

        Map<String, Object> res = new HashMap<String, Object>();
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<User> userPage = userRepository.findAll(page);
        res.put("data", userPage.getContent());
        res.put("Total No. of  pages", userPage.getTotalPages());
        res.put("Total no. of elements", userPage.getTotalElements());
        res.put("Current page No.", userPage.getNumber());
        return res;
    }

    public List<User> getUsersByFullname(String Fullname) {
        return userRepository.findByFullnameStartingWith(Fullname);
    }

    public TodoNotes addTodo(TodoNotes todo) {
        todoNotesRepository.save(todo);
        return todo;
    }

    public List<TodoNotes> getTodos() {
        List<TodoNotes> todos = todoNotesRepository.findAll();
        return todos;
    }
}
