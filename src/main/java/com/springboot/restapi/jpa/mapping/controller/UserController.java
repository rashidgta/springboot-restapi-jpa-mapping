package com.springboot.restapi.jpa.mapping.controller;

import com.springboot.restapi.jpa.mapping.entitiy.OrderDetail;
import com.springboot.restapi.jpa.mapping.entitiy.UserDetail;
import com.springboot.restapi.jpa.mapping.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> addUsers(@RequestBody UserDetail userDetail) {
        System.out.println("This is conflict testing");
        log.info("UserController : addUsers : user details {}", userDetail);
        UserDetail userDetail1 = userService.addUser(userDetail);
        if (userDetail1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<OrderDetail> orders = userDetail.getOrderDetail();
        if (orders.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order details not present in User details");
        return ResponseEntity.ok().body(userDetail1);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDetail>> getUsers() {
        List<UserDetail> userDetail1 = userService.getUser();
        if (userDetail1 == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(userDetail1));
    }

    @PutMapping ("/user/{userId}")
    public ResponseEntity<UserDetail> updateUsers(@RequestBody UserDetail userDetail,
                                                  @PathVariable ("userId")  int userId) {
       UserDetail userDetail1 = userService.updateUser(userDetail,userId);
       if (userDetail1 == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       return ResponseEntity.of(Optional.of(userDetail1));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("userId") int userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (FindException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
