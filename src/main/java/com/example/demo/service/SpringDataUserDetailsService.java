//package com.example.demo.service;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SpringDataUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        UserDetails admin = User.withUsername("123")
//                .password("{noop}123").roles("USER").build();
//        return admin;
//    }
//}