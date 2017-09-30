package com.fortune.repository;


import com.fortune.model.Nationality;
import com.fortune.model.TarrifUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {
}
