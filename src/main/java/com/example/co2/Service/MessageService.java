package com.example.co2.Service;

import com.example.co2.Dao.MessageRepository;
import com.example.co2.Entite.Courses;
import com.example.co2.Entite.Message;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.ServiceImpl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements MessageServiceImpl {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;

    @Override
    public Message addMessage(Message msg) {
        Message savesMsg=messageRepository.save(msg);
        return savesMsg;
    }



    public List<Message> gettAllMessage(){
      return   messageRepository.findAll();
    }


}
