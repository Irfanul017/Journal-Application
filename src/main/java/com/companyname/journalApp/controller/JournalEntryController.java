package com.companyname.journalApp.controller;

import com.companyname.journalApp.entity.JournalEntry;
import com.companyname.journalApp.entity.User;
import com.companyname.journalApp.service.JournalEntryService;
import com.companyname.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal") // creates a mapping to entire class
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;  // spring create obj (initialize) we have injection in this call

    @Autowired
    private UserService userService;
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String userName){ // localhost:8080/journal GET
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){ // localhost:8080/journal POST
        try{
            journalEntryService.saveEntry(myEntry, userName); // used journalEntryService to call save() of Service call
            return new ResponseEntity<>(myEntry ,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}") // here used path-variable myId to get url : journal/id/?
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){

        // ResponseEntity used to return request type code form server
        Optional<JournalEntry> journalEntry = journalEntryService.getByID(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId , @PathVariable String userName){ // ? bcoz wild card pattern no need to return types
        journalEntryService.deleteById(myId , userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity updateJournalEntryById(@PathVariable ObjectId id ,
                                                 @RequestBody JournalEntry newEntry ,
                                                 @PathVariable String userName){
         // first find that id store in local variable . Type Journal Entery coz pura document ayega
        JournalEntry old =journalEntryService.getByID(id).orElse(null);
        if (old != null) {
            /*
            if old id set title , if new entry ka title null nhi h and content h kuch , toh newEntry ka title set kardo
            */
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old); // used journalEntryService to call save() of Service call
            return new ResponseEntity<>(old , HttpStatus.OK); // old wale meh hi update kiya exisiting data
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
