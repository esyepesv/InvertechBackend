package com.example.invertech.infraestructure.rest.controllers;

import com.example.invertech.application.IUserService;
import com.example.invertech.application.UserUseCase;
import com.example.invertech.domain.dtos.UserEmailDTO;
import com.example.invertech.domain.dtos.UserUpdateEmailDTO;
import com.example.invertech.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;
    @Autowired
    private UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){

        return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers(){
        return new ResponseEntity<>(iUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/actualizaremail")
    public ResponseEntity<User> updateEmail(@RequestBody UserUpdateEmailDTO updateEmailDTO){

        return new ResponseEntity<>(userUseCase.updateEmail(updateEmailDTO), HttpStatus.OK);
    }

    @GetMapping("/userbyemail")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestBody UserEmailDTO emailDTO){
        return new ResponseEntity<>(iUserService.getUserByEmail(emailDTO.getEmail()), HttpStatus.OK);
    }

}
