package com.application.expertguru.controller;


import com.application.expertguru.DTO.UniversalResponseDTO;
import com.application.expertguru.DTO.UserDTO;
import com.application.expertguru.DTO.UserUpdateDTO;
import com.application.expertguru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        UniversalResponseDTO ur = userService.addUser(userDTO);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @GetMapping(value="/getUser", params="username")
    public ResponseEntity<?> getUsers(@RequestParam String username){
        UniversalResponseDTO ur = userService.getUsersWithName(username);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @GetMapping(value="/getUser", params="emailId")
    public ResponseEntity<?> getUser(@RequestParam String emailId){
        UniversalResponseDTO ur = userService.getUserByEmailId(emailId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value="/deleteUser", params="userId")
    public ResponseEntity<?> deleteUser(@RequestParam String userId){
        UniversalResponseDTO ur = userService.deleteUser(userId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @PostMapping(value="/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam UserUpdateDTO userUpdate){
        UniversalResponseDTO ur = userService.updateUser(userUpdate);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}
