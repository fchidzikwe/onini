package com.fortune.service;

import com.fortune.model.Message;
import com.fortune.model.User;

import java.util.List;

/**
 * @author fchidzikwe
 */
public interface MessageService {

    List<Message> findByHeaderLike(String header);

    Message findById(Long id);

    void save(Message message);

    List<Message> findByReaded(Integer read);

    List<Message> findByMessageFrom(User user);

    List<Message> findByMessageTo(User user);

    List<Message> findByMessageToAndRead(User user,Integer readed);

    void saveMessage(Message message);
}
