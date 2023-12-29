package com.example.co2.Controller;

import com.example.co2.Dao.RoleRepository;
import com.example.co2.Dao.UserRepository;
import com.example.co2.Dto.SignIn;
import com.example.co2.Entite.Role;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.MailSenderService;
import com.example.co2.Dto.RoleName;
import com.example.co2.Service.UserService;
import com.example.co2.jwt.JwtProvider;
import com.example.co2.jwt.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MailSenderService mailSending;

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignIn login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

    }
    @RequestMapping(value = "/signup/employee", method = RequestMethod.POST)
    public ResponseEntity<Userco2> registerUser(@Validated @RequestBody Userco2 user1) {
       return userService.registerUser(user1);
    }


    @RequestMapping(value = "/signupadmin", method = RequestMethod.POST)
    public ResponseEntity<Userco2> registerAdmin(@Valid @RequestBody Userco2 userco2)  {
        return userService.registerAdmin(userco2);
    }
}
















