package com.example.co2.Service.ServiceImpl;

import com.example.co2.Dto.RoleName;
import com.example.co2.Entite.Role;
import com.example.co2.Entite.Userco2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;

public interface UserServiceImpl {

    public List<Userco2> getAllUser() ;


    public List<Userco2> getUserByRoles(RoleName roleName);

    public Userco2 deleteUser(Long id);

    public ResponseEntity<Userco2> registerUser(Userco2 user1);



}
