package com.fortune.service.impl;


import com.fortune.model.Costs;
import com.fortune.model.Matter;
import com.fortune.repository.CostsRepository;
import com.fortune.repository.MatterRepository;
import com.fortune.service.CostsService;
import com.fortune.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostsServiceImpl implements CostsService {

    @Autowired
    CostsRepository costsRepository;


    @Override
    public void save(Costs costs) {
        costsRepository.save(costs);
    }

    @Override
    public void delete(Costs costs) {
        costsRepository.delete(costs);
    }


    @Override
    public List<Costs> findAllCosts() {
        return costsRepository.findAll();
    }

    @Override
    public Costs findCosts(String name) {
        return costsRepository.findByName(name);
    }

    @Override
    public Costs findCostsByDescription(String description) {
        return costsRepository.findByDescription(description);
    }
}
