package dev.franciscohernandez.lamptracker.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import dev.franciscohernandez.lamptracker.service.models.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
    
    @Query("{ 'username' : ?0 }")
    UserModel findByUsername(String username);    

    
}
