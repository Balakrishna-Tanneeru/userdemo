package com.bk.demo.Controller;

import com.bk.demo.Service.UserService;
import com.bk.demo.exception.BlogAlreadyExistsException;
import com.bk.demo.exception.BlogNotFoundException;
import com.bk.demo.model.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public List<UserRecord> getAllUser() throws BlogNotFoundException {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public void addUser(@RequestBody UserRecord userRecord) throws BlogAlreadyExistsException {
        userService.addUser(userRecord);
    }

    @PutMapping("/updateUser/{id}")
    public void replaceEmployee(@RequestBody UserRecord newAddress, @PathVariable Integer id) {


            userService.updateUser(newAddress, id);

    }

    @GetMapping("findUser/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable("id") int id) throws BlogNotFoundException {
        return new ResponseEntity<UserRecord>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

}







