package com.fortune.service.impl;

import com.fortune.model.Message;
import com.fortune.model.User;
import com.fortune.repository.MessageRepository;
import com.fortune.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fchidzikwe
 */
@Service
public class MessageServiceimpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public List<Message> findByHeaderLike(String header) {

        String searchTerm = "%"+ header+"%";
        return messageRepository.findByHeaderLike(searchTerm);
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public List<Message> findByReaded(Integer readed) {
        return messageRepository.findByReaded(readed);
    }

    @Override
    public List<Message> findByMessageFrom(User user) {
        return messageRepository.findByMessageFrom(user);
    }

    @Override
    public List<Message> findByMessageTo(User user) {
        return messageRepository.findByMessageTo(user);
    }

    @Override
    public List<Message> findByMessageToAndRead(User user, Integer readed) {
        return messageRepository.findByMessageToAndReaded(user,readed);
    }

    @Override
    public void saveMessage(Message message) {
        message.setRead(0);
        messageRepository.save(message);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }


}
