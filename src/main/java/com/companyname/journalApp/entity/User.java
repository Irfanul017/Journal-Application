package com.companyname.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users") // map obj of Users to Document in mondodb
@Data
public class User {

    @Id
    private ObjectId id; // can take string also spring data mongodb will convert to ObjectID
    @Indexed(unique = true)
    @NonNull
    private String userName; // created index of username and store unique value
    @NonNull // lombrok checks while setter and setter
    private String password;

    // each user will have multiple journal Entries
    @DBRef //  links journal entries to this list reference key or foreign key
    private List<JournalEntry> journalEntries = new ArrayList<>(); // As user created list also created no null list

}