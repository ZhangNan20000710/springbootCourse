package com.example.demo.dao;

import com.example.demo.domian.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IUserDao extends JpaRepository<Users,String>, JpaSpecificationExecutor<Users> {
    Users findByUsernameAndPassword(String username,String password);
    Users findByPhoneNumber(String phoneNumber);
    Users findByUsername(String username);
    Page<Users> findByUsernameLikeOrNicknameLikeOrPhoneNumberLikeOrEmailLike(String username,
                                                                  String nickname,
                                                                  String phoneNumber,
                                                                  String email,
                                                                  Pageable page);
}
