package com.fortune.service;

import com.fortune.model.Country;

import java.util.List;

/**
 * Created by ROZAY on 7/25/2017.
 */
public interface CountryService {
    List<Country> findAll();
    void saveCountry(Country country);
    void deleteCountry(Country country);
}
