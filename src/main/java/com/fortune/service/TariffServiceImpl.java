package com.fortune.service;

import com.fortune.model.Tariff;
import com.fortune.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */

@Service
public class TariffServiceImpl implements TariffService {

  @Autowired
  TariffRepository tariffRepository;

  @Override
  public void saveTariff(Tariff tariff) {
    tariffRepository.save(tariff);
  }

  @Override
  public void deleteTariff(Tariff tariff) {
     tariffRepository.delete(tariff);
  }

  @Override
  public List<Tariff> findAllTariff() {
    return tariffRepository.findAll();
  }
}
