package com.newpro.webpro.dao;

import com.newpro.webpro.model.TodoNotes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TodoNotesRepository extends MongoRepository<TodoNotes,String> {
    public List<TodoNotes> findByUserId(String userId);
}
