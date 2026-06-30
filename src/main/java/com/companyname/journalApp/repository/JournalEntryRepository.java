package com.companyname.journalApp.repository;

import com.companyname.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> { // journalEntry chahiye kua use karke id
    // mongoRepo is interface provided by spring data mongodb perform standard CRUD operations
// entity type JournalEntry and Id type ObjectID

    // just mapping ke liye repository use hota h



}
