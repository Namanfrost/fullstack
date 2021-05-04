package com.newpro.webpro.service;

import com.newpro.webpro.model.User;
import com.newpro.webpro.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {
    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(List.of(new User("123", "Naman", "naman@gmail.com", "123123"), new User("124", "David", "David@gmail.com", "121212")));
        assertEquals(2, service.getUsers().size());
    }
/*
    @Test
    public void getUserByNameTest() {
        when(repository.findByName("Naman")).thenReturn(
                List.of(
                        new User("123", "Naman", "naman@gmail.com", "123123"),
                        new User("124", "Naman", "Naman10@gmail.com", "121212")
                )
        );
        assertEquals(2, service.getUserByName("Naman").size());
    }
*/
    @Test
    public void getUserByIdTest() {
        User expected = new User("123", "Naman", "naman@gmail.com", "123123");
        when(repository.findById("123")).thenReturn(Optional.of(new User("123", "Naman", "naman@gmail.com", "123123")));
        assertEquals(expected, service.getUserById("123"));
    }

    @Test
    public void saveUserTest() {
        User user = new User("123", "Naman", "naman@gmail.com", "123123");
        User expected = new User("123", "Naman", "naman@gmail.com", "123123");
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
        User newUser = new User("123", "Naman", "naman@gmail.com", "123123");
        when(repository.save(newUser)).thenReturn(newUser);
        when(repository.findById(newUser.getId())).thenReturn(Optional.of(newUser));
        assertEquals(newUser, service.userUpdate(newUser));
        verify(repository, times(1)).save(newUser);
        verify(repository, times(1)).findById(newUser.getId());
    }
}
