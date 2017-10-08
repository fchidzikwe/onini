package com.fortune.service.impl;

import com.fortune.model.City;
import com.fortune.repository.CityRepository;
import com.fortune.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/25/17.
 */

@Service
public class CityServiceImpl implements CityService {
  @Autowired
  CityRepository cityRepository;

  @Override
  public void saveCity(City city) {
    cityRepository.save(city);
  }

  @Override
  public void deleteCity(City city) {
    cityRepository.delete(city);
  }

  @Override
  public List<City> findAllcities() {
    return cityRepository.findAll();
  }
}
