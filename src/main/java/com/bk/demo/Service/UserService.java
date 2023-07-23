package com.bk.demo.Service;

import com.bk.demo.Repository.UserRepository;
import com.bk.demo.exception.UserAlreadyExistsException;
import com.bk.demo.exception.UserNotFoundException;
import com.bk.demo.model.UserRecord;
//import io.leangen.graphql.annotations.GraphQLArgument;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@GraphQLApi
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserRecord> getAllUsers() throws UserNotFoundException {
        List<UserRecord> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        if((userRecords).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userRecords;
    }

    public void addUser(UserRecord userRecord) {
        if (userRepository.existsById(userRecord.getId())) {
            throw new UserAlreadyExistsException();
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
    public UserRecord getUserById(int id) throws UserNotFoundException {
        UserRecord user;
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException();
        } else {
            user = userRepository.findById(id).get();
        }
        return user;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}