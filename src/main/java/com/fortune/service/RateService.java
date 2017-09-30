package com.fortune.service;

import com.fortune.model.Rate;
import com.fortune.model.User;

import java.util.List;

/**
 * @author fchidzikwe on 9/16/17
 */
public interface RateService {

    Rate findByUser(User user );

    List<Rate> findAll();

    void save(Rate rate);
}
