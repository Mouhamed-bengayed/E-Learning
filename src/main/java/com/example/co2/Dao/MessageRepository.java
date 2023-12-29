package com.example.co2.Dao;

import com.example.co2.Entite.Administrator;
import com.example.co2.Entite.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
