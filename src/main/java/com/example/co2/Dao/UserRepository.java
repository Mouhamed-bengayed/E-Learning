package com.example.co2.Dao;


import com.example.co2.Entite.Role;
import com.example.co2.Entite.Userco2;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Userco2,Long> {
    Userco2 findByEmail(String username);
    Optional<Userco2> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Userco2> findByRolesContains(Role role);

}
