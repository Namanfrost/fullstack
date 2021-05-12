package com.newpro.webpro.service;

import com.newpro.webpro.model.User;
import com.newpro.webpro.dao.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    User user1 =new User("123", "Naman", "naman@gmail.com", 23, "123123");
    User user2 =new User("124", "David", "david@gmail.com", 24, "121212");
    User user3 =new User("125", "Robin", "robin@gmail.com", 23, "123123");
    User user4 =new User("126", "Rahul", "rahul@gmail.com", 24, "121212");
    User user5 =new User("127", "David Miller", "davidmiller@gmail.com", 25, "121213");

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(List.of(user1, user2));
        assertEquals(2, service.getUsers().size());
    }

    @Test
    public void getUserByIdTest() {
        User expected = user1;
        when(repository.findById("123")).thenReturn(Optional.of(user1));
        assertEquals(expected, service.getUserById("123"));
    }

    @Test
    @DisplayName("Test should pass when there is no problem in saving the new user")
    public void saveUserTest() {
        User user = user1;
        User expected = user1;
        when(repository.save(user)).thenReturn(user);
        assertEquals(expected, service.addUser(user));
    }

    @Test
    public void deleteUserTest() {
        String Id = "123";
        service.deleteUser(Id);
        verify(repository, times(1)).deleteById(Id);
    }

    @Test
    public void updateUserTest() {
        User newUser = user1;
        when(repository.save(newUser)).thenReturn(newUser);
        when(repository.findById(newUser.getId())).thenReturn(Optional.of(newUser));
        assertEquals(newUser, service.userUpdate(newUser));
        verify(repository, times(1)).save(newUser);
        verify(repository, times(1)).findById(newUser.getId());
    }

    @Test
    public void getUsersByPageTest() {
        int pageNo = 2;
        int pageSize = 4;
        String sortBy = "Name";
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<User> dummy = new PageImpl<User>(List.of(user1, user2), page, 2);

        given(repository.findAll(Mockito.any(PageRequest.class))).willReturn(dummy);
        Map<String, Object> res = service.getUsersByPage(pageNo, pageSize, sortBy);
        verify(repository, times(1)).findAll(page);

    }
    @Test
    public void getUsersByPageOutputTest() {
        int pageNo = 1;
        int pageSize = 2;
        String sortBy = "Name";
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<User> dummy = new PageImpl<User>(List.of(user1, user2,user3,user4), page, 4);
        given(repository.findAll(Mockito.any(PageRequest.class))).willReturn(dummy);
        Map<String, Object> res = service.getUsersByPage(pageNo, pageSize, sortBy);

        assertEquals(2,res.get("Total No. of  pages"));
        assertEquals(1,res.get("Current page No."));
        assertEquals(4L,res.get("Total no. of elements"));
    }

    // check if the test able to complete before the timeout
    @Test(timeout = 10000)
    public void testSort_Performance(){
        for (int i = 0; i < 10000; i++) {
            when(repository.findAll()).thenReturn(List.of(user1, user2));
            assertEquals(2, service.getUsers().size());
        }
    }

    @Test
    public void getUsersByFullnameTest() {
        String name = "David";
        when(repository.findByFullnameStartingWith(name)).thenReturn(List.of(user2, user5));
        List<User> users =  service.getUsersByFullname(name);
        for (int i=0;i<users.size();i++)
        {
            System.out.println(users.get(i).getFullname() );

            assertTrue(users.get(i).getFullname().contains("David"));
        }

    }
}
