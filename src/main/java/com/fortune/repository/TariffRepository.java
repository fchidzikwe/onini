package com.fortune.repository;

import com.fortune.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fortune on 7/26/17.
 */

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
