package com.example.dao;


import org.springframework.stereotype.Component;

import com.example.dao.impl.BookDaoImpl;

@Component("user")
public class User implements BookDaoImpl {

    public User() {
    }

    @Override
    public void say() {
        System.out.println("this user");
    }
}