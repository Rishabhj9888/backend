package com.edigest.hloo.controller;

import com.edigest.hloo.Entity.entry;
import com.edigest.hloo.Service.UserService;
import com.edigest.hloo.jwt.JwtUtils;
import com.edigest.hloo.jwt.LoginRequest;
import com.edigest.hloo.jwt.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
public class UserController {
    @Autowired
    public UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/show")
    public List<entry> getAll() {

        return service.getAll();
    }


    @PostMapping("/write")
    public boolean createEntry(@RequestBody entry myentry) {
        service.saveEntry(myentry);
        return true;
    }

    @GetMapping("/Id/{MyId}")
    public ResponseEntity<?> getEntryByID(@PathVariable String MyId) {
        Optional<entry> Entry = service.findById(MyId);

        if (Entry.isPresent()) {
            return new ResponseEntity<>(Entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/checkPass/{id}")
    public ResponseEntity<?> checkPassword(@PathVariable String id, @RequestParam("password") String password) {

        boolean passwordCorrect = service.checkPassword(id, password);
        if (passwordCorrect) {
            return ResponseEntity.ok("Password is correct");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }
    @PostMapping("/request")
    public entry request(@RequestBody entry entry){
        return service.saveEntry(entry);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String endPoint(){
        return "only for user access";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String anotherEndPoint(){
        return "only for admin access";
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try {
            authentication=authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        }
        catch (AuthenticationException exception){
            Map<String,Object>map=new HashMap<>();
            map.put("Message","Bad Credentials");
            map.put("Status",false);
            return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        String jwtoken=jwtUtils.generateTokenFromUser(userDetails);
        List<String>roles=userDetails.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response=new LoginResponse(userDetails.getUsername(),roles,jwtoken);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/testing")
    public String testingCase(){
        return  " testing is done";
    }





}
