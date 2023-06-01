package com.example.springbootexceptions.controller;

import com.example.springbootexceptions.database.entity.UserEntity;
import com.example.springbootexceptions.database.repo.IUserRepo;
import com.example.springbootexceptions.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {
    @Autowired
    private IUserRepo iUserRepo;

    @PostMapping("insert")
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity userEntity) {
        iUserRepo.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("getUserByName")
    public ResponseEntity<?> getUserByName(@RequestParam String name) {
        UserEntity userEntity = iUserRepo.myGetUserByName(name);
        if (userEntity.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userEntity);
        }
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("getUserLikeName")
    public ResponseEntity<?> getUserLikeName(@RequestParam String name) {
        List<UserEntity> userEntities = iUserRepo.myGetUserLikeName(name);
        if (userEntities == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userEntities);
        }
        return ResponseEntity.ok(userEntities);
    }

    @GetMapping("getUserById")
    public ResponseEntity<UserEntity> getUserById(@RequestParam Integer id) {
        UserEntity userEntity = iUserRepo.findById(id).orElseThrow(
                () -> new AppException("", HttpStatus.NOT_FOUND)
        );
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("getAll")
    public List<UserEntity> getAllUsers() {
        return iUserRepo.findAll();
    }
}
