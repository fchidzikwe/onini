package com.fortune.service.impl;

import com.fortune.model.Rate;
import com.fortune.model.User;
import com.fortune.repository.RateRepository;
import com.fortune.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fchidzikwe on 9/16/17
 */

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    RateRepository rateRepository;

    @Override
    public Rate findByUser(User user) {
        return rateRepository.findByUser(user);
    }

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public void save(Rate rate) {
        rateRepository.save(rate);
    }
}
