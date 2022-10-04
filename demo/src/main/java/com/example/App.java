package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.BookService;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// 获取IoC容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// User user = (User)ctx.getBean("user");
		BookService bookService =ctx.getBean(BookService.class);
		bookService.say();
		// user.say();
		ctx.close();
	}
}
