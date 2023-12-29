package com.example.co2.Service;

import com.example.co2.Dao.RoleRepository;
import com.example.co2.Dao.UserRepository;
import com.example.co2.Dto.RoleName;
import com.example.co2.Entite.Role;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.*;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailSenderService mailSending;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;


    public List<Userco2> getAllUser() {
        return userRepository.findAll();
    }

    public Userco2 getUserById(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Provider ID not Found"));
    }

    public List<Userco2> getUserByRoles(RoleName roleName){
        Role role= roleRepository.findByName(roleName).get();
        return userRepository.findByRolesContains(role);
    }

    public Userco2 deleteUser(Long id) {
        Optional<Userco2> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }



    public ResponseEntity<Userco2> registerUser(Userco2 user1) {
        if (userRepository.existsByUsername(user1.getUsername())) {
            return new ResponseEntity<Userco2>(HttpStatus.NOT_FOUND);
        }
        if (userRepository.existsByEmail(user1.getEmail())) {
            return new ResponseEntity<Userco2>(HttpStatus.BAD_REQUEST);
        }
        Userco2 user = new Userco2(user1.getName(), user1.getUsername(), user1.getEmail(), passwordEncoder.encode(user1.getPassword()), false, user1.getAddress(), false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_Employee)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user.setRoles(roles);
        user.setValid(true);
        Userco2 suser = userRepository.save(user);
        if (suser != null) {
            String Newligne = System.getProperty("line.separator");
            String url = "http://localhost:4200/#/verification" ;
            String body = "Soyez le bienvenue dans notre platforme ECOtalan  \n  veuillez utuliser ce lien là pour s'authentifier :" + Newligne + url + Newligne + "verification" +
                    "Voici votre code de verfication  TN1122" ;
            try {
                mailSending.send(user.getEmail(), "Welcome", body);
                return new ResponseEntity<Userco2>(user, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Userco2> registerAdmin(@Valid @RequestBody Userco2 user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<Userco2>(HttpStatus.NOT_FOUND);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<Userco2>(HttpStatus.NOT_FOUND);
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        Userco2 user1 = new Userco2(user.getName(), user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()), false, user.getAddress(), false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user1.setRoles(roles);
        userRepository.save(user1);
        return new ResponseEntity<Userco2>(user1, HttpStatus.OK);
    }

    public Optional<Userco2> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsername(username);
    }

}



