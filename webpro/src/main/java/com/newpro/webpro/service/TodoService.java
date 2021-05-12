package com.newpro.webpro.service;
import com.newpro.webpro.dao.TodoNotesRepository;
import com.newpro.webpro.model.TodoNotes;
import com.newpro.webpro.model.User;

import java.util.ArrayList;
import java.util.List;

public class TodoService {

    private TodoNotesRepository todoNotesRepository;

    public TodoService(TodoNotesRepository todoNotesRepository) {
        this.todoNotesRepository = todoNotesRepository;
    }

    public List<TodoNotes> retrieveTodoRelatedToSpring(User user){
        List<TodoNotes> filteredTodos=new ArrayList<TodoNotes>();
        List<TodoNotes> todos=todoNotesRepository.findByUserId(user.getId());
        for(TodoNotes todoOb:todos){
            String todo = todoOb.getDescription();
            if( todo.contains("Spring")){
                filteredTodos.add(todoOb);
            }
        }
        return filteredTodos;
    }

    public void deleteTodoNotReletedToSpring(User user) {
        List<TodoNotes> filteredTodos = new ArrayList<TodoNotes>();
        List<TodoNotes> todos = todoNotesRepository.findByUserId(user.getId());
        for (TodoNotes todoOb : todos) {
            String todo = todoOb.getDescription();
            if (!todo.contains("Spring")) {
                todoNotesRepository.deleteById(todoOb.getId());
            }
        }
    }

}
