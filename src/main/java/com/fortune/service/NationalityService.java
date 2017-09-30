package com.fortune.service;

import com.fortune.model.Country;
import com.fortune.model.Nationality;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */


public interface NationalityService {

  List<Nationality> findAll();
  void saveNationality(Nationality nationality);
  void deleteNationality(Nationality nationality);
}
