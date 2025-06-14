package com.springboot.restapi.jpa.mapping.service;

import com.springboot.restapi.jpa.mapping.entitiy.UserDetail;
import com.springboot.restapi.jpa.mapping.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserDetailRepository userDetailRepo;

    public UserDetail addUser(UserDetail userDetail) {
        userDetailRepo.save(userDetail);
        return userDetail;
    }

    public List<UserDetail> getUser() {
        List<UserDetail> allUsers = userDetailRepo.findAll();
        return allUsers;
    }

    public UserDetail updateUser(UserDetail userDetail, int userId) {
        Optional<UserDetail> userById = userDetailRepo.findById(userId);
        if ((userId == userDetail.getUserId()) && userById.isPresent()) {
            return userDetailRepo.save(userDetail);
        }
        return null;
    }

    public void deleteUser(int userId) {
        Optional<UserDetail> userById = userDetailRepo.findById(userId);
        if (userById.isEmpty()) {
            throw new FindException("User not found in DB");
        }
        userDetailRepo.deleteById(userId);
    }
}

