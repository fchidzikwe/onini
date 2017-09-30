package com.fortune.service;

import com.fortune.model.City;

import java.util.List;

/**
 * Created by fortune on 7/25/17.
 */

public interface CityService {
  void saveCity(City city);
  void deleteCity(City city);
  List<City> findAllcities();
}
