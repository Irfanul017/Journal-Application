package com.companyname.journalApp.service;

import com.companyname.journalApp.entity.JournalEntry;
import com.companyname.journalApp.entity.User;
import com.companyname.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// Main business Logic here
@Component // help to create bean (object)of this class without initializing obj again and again
public class JournalEntryService {
    // controller -- > service--> repository
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional // all operation executes or non.New entry and save updates user
    public void saveEntry(JournalEntry journalEntry, String userName){ // journalentry variable uska type JournalEntry hoga bcoz object ayega yaha
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry); // save stored
            user.getJournalEntries().add(saved); // add to user the entry
            userService.saveUser(user);// saved user using service class


    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
        // Used to update using id
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll(); // repository used to get all collection

    }

    public Optional<JournalEntry> getByID(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id)); // cascading deletion in mongoDb
        userService.saveUser(user);// save updated user after deletion of entries
        journalEntryRepository.deleteById(id);

    }
}
