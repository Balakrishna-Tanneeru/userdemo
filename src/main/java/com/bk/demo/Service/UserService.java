package com.bk.demo.Service;

import com.bk.demo.Repository.UserRepository;
import com.bk.demo.exception.BlogAlreadyExistsException;
import com.bk.demo.exception.BlogNotFoundException;
import com.bk.demo.model.UserRecord;
//import io.leangen.graphql.annotations.GraphQLArgument;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@GraphQLApi
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserRecord> getAllUsers() throws BlogNotFoundException {
        List<UserRecord> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        if((userRecords).isEmpty()) {
            throw new BlogNotFoundException();
        }
        return userRecords;
    }

    public void addUser(UserRecord userRecord) {
        if (userRepository.existsById(userRecord.getId())) {
            throw new BlogAlreadyExistsException();
        }
        userRepository.save(userRecord);
    }

/*    @GraphQLMutation(name="autenticateUser")
    public UserRecord authenticateUser(@GraphQLArgument(name="user") UserRecord userRecord) {
        if (userRepository.existsById(userRecord.getId())) {
            throw new BlogAlreadyExistsException();
        }
        userRepository.save(userRecord);
        return userRecord;
    }*/


    public UserRecord updateUser(UserRecord newAddress, Integer id) {
        return userRepository.findById(id)
                .map(address -> {
                    address.setName(newAddress.getName());
                    address.setEmail(newAddress.getEmail());
                    return userRepository.save(address);
                })
                .orElseGet(() -> {
                    return userRepository.save(newAddress);
                });


    }

   // @Override
    public UserRecord getUserById(int id) throws BlogNotFoundException {
        UserRecord user;
        if (userRepository.findById(id).isEmpty()) {
            throw new BlogNotFoundException();
        } else {
            user = userRepository.findById(id).get();
        }
        return user;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}