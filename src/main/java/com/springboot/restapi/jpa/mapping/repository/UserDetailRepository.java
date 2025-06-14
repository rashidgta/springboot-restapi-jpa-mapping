package com.springboot.restapi.jpa.mapping.repository;

import com.springboot.restapi.jpa.mapping.entitiy.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

}
