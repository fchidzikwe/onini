package com.fortune.repository;

import com.fortune.model.Rate;
import com.fortune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fchidzikwe on 9/16/17
 */
public interface RateRepository extends JpaRepository<Rate, Long> {

    Rate findByUser(User user);

    List<Rate> findAll();
}
