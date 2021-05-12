package com.newpro.webpro.dao;

import com.newpro.webpro.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    List<User> findByFullnameStartingWith(String Fullname);
}

//mongo repository already have predefine curd opretion