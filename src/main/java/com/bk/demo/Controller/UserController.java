package com.bk.demo.Controller;

import com.bk.demo.Service.UserService;
import com.bk.demo.exception.UserAlreadyExistsException;
import com.bk.demo.exception.UserNotFoundException;
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
   // @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/UserList")
    public List<UserRecord> getAllUser() throws UserNotFoundException {
        return userService.getAllUsers();
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public void addUser(@RequestBody UserRecord userRecord) throws UserAlreadyExistsException {
        userService.addUser(userRecord);
    }
 //   @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateUser/{id}")
    public void replaceEmployee(@RequestBody UserRecord newAddress, @PathVariable Integer id) {


            userService.updateUser(newAddress, id);

    }

 //   @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("findUser/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        return new ResponseEntity<UserRecord>(userService.getUserById(id), HttpStatus.OK);
    }
  //  @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
// github test.....
}







