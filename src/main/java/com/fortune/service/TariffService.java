package com.fortune.service;

import com.fortune.model.Tariff;

import java.util.List;

/**
 * Created by fortune on 7/26/17.
 */

public interface TariffService {

  void saveTariff(Tariff tariff);

  void deleteTariff(Tariff tariff);

  List<Tariff> findAllTariff();
}
