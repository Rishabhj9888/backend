package com.edigest.hloo;

import com.edigest.hloo.Entity.entry;
import com.edigest.hloo.Repository.MyRepository;
import com.edigest.hloo.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.mockito.Mockito.when;


public class UserServiceTestMokito {
    @InjectMocks
    public UserService userService;

    @Mock
    public MyRepository repo;
    private entry user1;
    private entry user2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
         user1=new entry("123","password1");
        user2=new entry("987","password2");

    }
    @Test
    public void checkPasswordTest(){
        when(repo.findById("123")).thenReturn(Optional.of(user1));
        boolean result= userService.checkPassword("123","password65");
        Assertions.assertTrue(result,"password is wrong");


    }






}
