package com.example.co2.Entite;

import lombok.Data;


import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Userco2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private int number;
    private Boolean blocked;
    private boolean valid;
    private String token;

    @OneToMany(mappedBy = "userco2")
    private List<Courses> courses;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Userco2(String name, String username, String email, String password, boolean blocked, String address, boolean valid) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
        this.valid = valid;

    }

    public Userco2() {

    }
}
