package com.example.co2.Controller;



import com.example.co2.Dto.RoleName;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

@Autowired
UserService userService;

    @GetMapping("/list-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Userco2> ListUser() {
        return userService.getAllUser();
    }



    @DeleteMapping("/delete-user/{idUser}")
    public void deleteAccount(@PathVariable("idUser") Long idUser) {
        userService.deleteUser(idUser);
    }

    @GetMapping("/list-RolesName/{RolesName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Userco2> ListUserByRoles(@PathVariable("RolesName") RoleName roleName) {
        return userService.getUserByRoles(roleName);
    }


}
