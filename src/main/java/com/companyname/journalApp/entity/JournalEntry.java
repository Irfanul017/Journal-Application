package com.companyname.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries") // map obj of JournalEntry to Document in mondodb
@Data
public class JournalEntry {
    @Id
    private ObjectId id; // uses mongodb ObjectID
    @NonNull
    private  String title;
    private String content;

    private LocalDateTime date;


}
