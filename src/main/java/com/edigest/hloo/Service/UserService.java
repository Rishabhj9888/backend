package com.edigest.hloo.Service;

import com.edigest.hloo.Entity.entry;
import com.edigest.hloo.Repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public MyRepository repo;
    public BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    public entry saveEntry(entry entry){
       entry.setPassword(encoder.encode(entry.getPassword()));
        repo.save(entry);
        return entry;
    }
    public List<entry> getAll(){
        return  repo.findAll();
    }

    public Optional<entry> findById(String id){
        return  repo.findById(id);
    }
    public boolean checkPassword(String id, String password) {
        Optional<entry> entryOptional = repo.findById(id);
        return entryOptional.map(entry -> entry.getPassword().equals(password)).orElse(false);
    }
    public void checking(){
        System.out.println("all is fine");
    }

    }

