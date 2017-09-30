package com.fortune.repository;

/**
 * @author fchidzikwe on 9/6/17
 */


import com.fortune.model.FacilityConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface FacilityConfigurationRepository extends JpaRepository<FacilityConfiguration, Long> {

    @Override
    FacilityConfiguration findOne(Long aLong);


    @Query("select f from FacilityConfiguration  f where f.name =:name")
    FacilityConfiguration findByName(@Param("name") String name);

    @Query("select f from FacilityConfiguration  f where f.email =:email")
    FacilityConfiguration findByEmail(@Param("email") String email);

    @Query("select f from FacilityConfiguration  f where f.website =:website")
    FacilityConfiguration findByWebsite(@Param("website") String website);


    @Modifying
    @Transactional
    @Query("update FacilityConfiguration f set f.profilePicture = ?2 where f.email = ?1")
    int updateProfilePicture(String email, String profilePicture);


}
