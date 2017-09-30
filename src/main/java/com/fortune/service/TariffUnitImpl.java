package com.fortune.service;

import com.fortune.model.TarrifUnit;
import com.fortune.repository.TariffUintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/25/17.
 */

@Service
public class TariffUnitImpl implements TariffUnitService {
  @Autowired
  TariffUintRepository tariffUintRepository;

  @Override
  public void saveTariffUnit(TarrifUnit tarrifUnit) {
    tariffUintRepository.save(tarrifUnit);
  }

  @Override
  public void deleteTariffUnit(TarrifUnit tarrifUnit) {
    tariffUintRepository.delete(tarrifUnit);
  }

  @Override
  public List<TarrifUnit> findAllTariffUnits() {
    return tariffUintRepository.findAll();
  }
}
