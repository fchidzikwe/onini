package com.fortune.service.impl;

import com.fortune.model.Nationality;
import com.fortune.repository.NationalityRepository;
import com.fortune.service.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */

@Service
public class NationalityServiceImpl implements NationalityService {
  @Autowired
  NationalityRepository nationalityRepository;

  @Override
  public List<Nationality> findAll() {
    return nationalityRepository.findAll();
  }

  @Override
  public void saveNationality(Nationality nationality) {
      nationalityRepository.save(nationality);
  }

  @Override
  public void deleteNationality(Nationality nationality) {
    nationalityRepository.delete(nationality);
  }
}
