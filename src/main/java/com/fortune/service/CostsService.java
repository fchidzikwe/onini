package com.fortune.service;

import com.fortune.model.Costs;
import com.fortune.model.Matter;

import java.util.List;

public interface CostsService {

        void save(Costs matter);

        void delete(Costs matter);


    List<Costs> findAllCosts();

    Costs findCosts(String cost);

    Costs findCostsByDescription(String cost);
}
