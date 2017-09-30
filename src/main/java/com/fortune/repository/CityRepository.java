package com.fortune.repository;

import com.fortune.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fortune on 7/25/17.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
