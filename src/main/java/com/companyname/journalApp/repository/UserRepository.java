package com.companyname.journalApp.repository;

import com.companyname.journalApp.entity.JournalEntry;
import com.companyname.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<com.companyname.journalApp.entity.User, ObjectId> { // journalEntry chahiye kua use karke id
    // mongoRepo is interface provided by spring data mongodb perform standard CRUD operations
    User findByUserName(String username);



}
