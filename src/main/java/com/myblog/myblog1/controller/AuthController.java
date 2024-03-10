package com.myblog.myblog1.controller;
import com.myblog.myblog1.entity.Role;
import com.myblog.myblog1.entity.User;
import com.myblog.myblog1.payload.LoginDto;
import com.myblog.myblog1.payload.SignUpDto;
import com.myblog.myblog1.repository.RoleRepository;
import com.myblog.myblog1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository  roleRepository;
    @PostMapping("/Signin")
    public ResponseEntity<String>authenticateUser(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<> ("User signed in Sucessifully",HttpStatus.OK);

    }
    @PostMapping("/SignUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<> ("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<> ("Email is already taken!",HttpStatus.BAD_REQUEST);
        }
        User user =new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        Role roles = roleRepository.findByName(signUpDto.getRoleType()).get();

        Set<Role> convertRoleToSet = new HashSet<>();
        convertRoleToSet.add(roles);
        user.setRoles(convertRoleToSet);

        userRepository.save(user);

        return new ResponseEntity<>("User registered Sucessfully",HttpStatus.OK);
    }
}

