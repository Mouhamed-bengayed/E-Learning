package com.example.co2.Service.ServiceImpl;

import com.example.co2.Entite.Message;
import com.example.co2.Entite.Userco2;

import java.util.List;
import java.util.Optional;

public interface MessageServiceImpl {


    public Message addMessage(Message m1);

    public List<Message> gettAllMessage();

}
