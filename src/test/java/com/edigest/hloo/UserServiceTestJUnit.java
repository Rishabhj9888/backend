package com.edigest.hloo;

import com.edigest.hloo.Entity.entry;
import com.edigest.hloo.Repository.MyRepository;
import com.edigest.hloo.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestJUnit {
    @Autowired
    public UserService UserService;
    @Autowired
    public MyRepository repo;



@BeforeEach
    public void setUp(){
     entry user1=new entry("124","password12");
        UserService.saveEntry(user1);

        entry user2=new entry("567","password56");
        UserService.saveEntry(user2);
    }
    @Test
    public void  findByIdTest(){
        Assertions.assertNotNull( UserService.findById("124"));
    }
    @Test
     public void checkPasswordTest(){
         boolean result =UserService.checkPassword("567","password56");
         Assertions.assertTrue(result,"password is correct");

     }




}
