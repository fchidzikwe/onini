package com.fortune.service;

import com.fortune.model.Message;
import com.fortune.model.User;

import java.util.List;

/**
 * @author fchidzikwe
 */
public interface MessageService {

    List<Message> findByHeaderLike(String header);


    List<Message> findByReaded(Integer read);

    List<Message> findByMessageFrom(User user);

    List<Message> findByMessageTo(User user);

    void saveMessage(Message message);
}
