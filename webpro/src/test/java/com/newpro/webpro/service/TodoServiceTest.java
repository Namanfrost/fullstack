package com.newpro.webpro.service;

import com.newpro.webpro.dao.TodoNotesRepository;
import com.newpro.webpro.model.TodoNotes;
import com.newpro.webpro.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TodoServiceTest {


    @Rule //use Rule to id you need more than one runner because @RunWith can only run one runner
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoNotesRepository todoNotesRepositoryMock;// mocking Repo TodoService

    @InjectMocks //injecting mock todoNotesRepositoryMock  inside TodoService
    TodoService todoService ;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;  //Declare argument captor

    User user1 =new User("123", "Naman", "naman@gmail.com", 23, "123123");
    User user2 =new User("124", "David", "david@gmail.com", 24, "121212");

    List<TodoNotes> todos = Arrays.asList(
            new TodoNotes("201","Spring","Learn Spring Boot" ,"Tuesday" , "123"),
            new TodoNotes("202","Spring MVC","Learn Spring MVC with Maven Build" ,"Sunday" , "123"),
            new TodoNotes("203","Let C++","Learn C++ with Maven Build" ,"Sunday" , "123")
            );


    @Test
    public void testRetrieveTodoRelatedToSpring_usingAMockBDD(){
        //given
        // given retrieveTodos call with user1 argument will return todos list
        given(  todoNotesRepositoryMock.findByUserId(user1.getId()) ).willReturn(todos);
        //TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //when
        List<TodoNotes> filteredTodos = todoService.retrieveTodoRelatedToSpring(user1);

        //then
        assertEquals( 2,filteredTodos.size());
    }

    @Test
    public void testDeleteTodoNotRelatedToSpring_usingBDD(){
        //given
        // given retrieveTodos call with user1 argument will return todos list
        given(todoNotesRepositoryMock.findByUserId(user1.getId())).willReturn(todos);
        //when
        todoService.deleteTodoNotReletedToSpring(user1);

        //then
        verify(todoNotesRepositoryMock).deleteById(todos.get(2).getId());
        verify(todoNotesRepositoryMock,times(1)).deleteById(todos.get(2).getId());
        verify(todoNotesRepositoryMock,never()).deleteById(todos.get(0).getId());
    }

    @Test
    public void testDeleteTodoNotRelatedToSpring_argumentCapture(){
        //given
        //TodoService todoServiceMock = mock(TodoService.class); // mocking class TodoService

        // given retrieveTodos call with user1 argument will return todos list
        given(todoNotesRepositoryMock.findByUserId(user1.getId())).willReturn(todos);

        //when
        todoService.deleteTodoNotReletedToSpring(user1);

        //capture the argument
        then(todoNotesRepositoryMock).should(times(1)).deleteById(stringArgumentCaptor.capture());

        assertEquals(1,stringArgumentCaptor.getAllValues().size());

    }
}
