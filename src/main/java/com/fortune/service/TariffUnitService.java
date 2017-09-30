package com.fortune.service;

import com.fortune.model.TarrifUnit;

import java.util.List;

/**
 * Created by fortune on 7/25/17.
 */
public interface TariffUnitService {
  void saveTariffUnit(TarrifUnit tarrifUnit);

  void deleteTariffUnit(TarrifUnit tarrifUnit);

  List<TarrifUnit> findAllTariffUnits();

}
