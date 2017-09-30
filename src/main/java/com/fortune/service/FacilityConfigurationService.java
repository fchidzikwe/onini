package com.fortune.service;


import com.fortune.model.FacilityConfiguration;

import java.util.List;
import java.util.Optional;

/**
 * @author fchidzikwe on 9/6/17
 */



public interface FacilityConfigurationService {

    FacilityConfiguration findByID(Long facilityId);

    FacilityConfiguration findByFacilityName(String facilityName);

    FacilityConfiguration findByFacilityEmail(String facilityEmail);

    FacilityConfiguration findByFacilityWebsite(String facilityWebsite);

    void saveFacility(FacilityConfiguration facilityConfiguration);

    Optional<List<FacilityConfiguration>> findAllFacilities();

    void updateProfilePicture(FacilityConfiguration facilityConfiguration, String profilePicture);


}
