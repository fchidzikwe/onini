package com.fortune.service;

import com.fortune.model.Matter;

import java.util.List;

public interface MatterService {

    public void save(Matter matter);

    public  void delete(Matter matter);


    List<Matter> findAllMatters();

    Matter findMatter(String matter);

    Matter findMatterByDescription(String matter);
}
