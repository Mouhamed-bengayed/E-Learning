package com.example.co2.Controller;

import com.example.co2.Entite.Message;
import com.example.co2.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/message")
@RestController
public class MessageController {


    @Autowired
    MessageService messageService;


    @GetMapping("/list-message")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Message> ListUserByRoles() {
        return messageService.gettAllMessage();
    }

    @PostMapping("/add-message")
    public Message addMessage(@RequestBody @Valid Message m) {
        return messageService.addMessage(m);
    }

}
