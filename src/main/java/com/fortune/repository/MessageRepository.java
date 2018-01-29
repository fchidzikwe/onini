package com.fortune.repository;

import com.fortune.model.Message;
import com.fortune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fchidzikwe
 */
public interface MessageRepository extends JpaRepository<Message, Long>{

    List<Message> findByHeaderLike(String header);

    List<Message> findByReaded(Integer readed);

    List<Message> findByMessageFrom(User user);

    List<Message> findByMessageToAndReaded(User user,Integer readed);

    List<Message> findByMessageTo(User user);


    Message findById(Long id);
}
