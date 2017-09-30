package com.fortune.repository;


import com.fortune.model.Matter;
import com.fortune.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatterServiceImpl implements MatterService {

    @Autowired
    MatterRepository matterRepository;


    @Override
    public void save(Matter matter) {
        matterRepository.save(matter);
    }

    @Override
    public void delete(Matter matter) {
        matterRepository.delete(matter);
    }

    @Override
    public List<Matter> findAllMatters() {
        return matterRepository.findAll();
    }

    @Override
    public Matter findMatter(String name) {
        return matterRepository.findByName(name);
    }

    @Override
    public Matter findMatterByDescription(String description) {
        return matterRepository.findByDescription(description);
    }
}
