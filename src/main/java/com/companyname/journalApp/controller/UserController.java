package com.companyname.journalApp.controller;

import com.companyname.journalApp.entity.User;
import com.companyname.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user") // creates a mapping to entire class
public class UserController {

// Will use userService to get all functions
    @Autowired
    private UserService userService;

    // all user
    @GetMapping
    public List<User> getAllUser(){
       return userService.getAll();
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.saveUser(user);

    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){ // get by username and password directly ro find user
        User userInDB =userService.findByUserName(userName);
        if(userInDB != null){
           userInDB.setUserName(user.getUserName()); // RequestBody parameters
           userInDB.setPassword(user.getPassword());
           userService.saveUser(userInDB); // stores in same of user
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
