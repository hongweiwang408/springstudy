package com.example.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.example.service.impl.BookServiceImpl;

@Controller

public class BookService implements BookServiceImpl {

    public BookService() {
    }

    public void say() {

        System.out.println("this service");
    }

}
