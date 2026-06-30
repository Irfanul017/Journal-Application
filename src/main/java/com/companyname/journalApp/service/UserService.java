package com.companyname.journalApp.service;


import com.companyname.journalApp.entity.User;
import com.companyname.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


// Main business Logic here
@Component // help to create bean (object)of this class without initializing obj again and again
public class UserService {
    // controller -- > service--> repository
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){

        try{

            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public List<User> getAll(){
        return userRepository.findAll(); // repository used to get all user

    }

    public Optional<User> getByID(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);

    }

    public User findByUserName(String userName){
        return (User) userRepository.findByUserName(userName);
    }
}
