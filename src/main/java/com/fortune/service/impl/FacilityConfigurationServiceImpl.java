package com.fortune.service.impl;

/**
 * @author fortune on 9/6/17
 */

import com.fortune.model.FacilityConfiguration;
import com.fortune.repository.FacilityConfigurationRepository;
import com.fortune.service.FacilityConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityConfigurationServiceImpl implements FacilityConfigurationService {

    @Autowired
    FacilityConfigurationRepository facilityConfigurationRepository;

    @Override
    public FacilityConfiguration findByID(Long facilityId) {
        return facilityConfigurationRepository.findOne(facilityId);
    }

    @Override
    public FacilityConfiguration findByFacilityName(String facilityName) {
        return facilityConfigurationRepository.findByName(facilityName);
    }

    @Override
    public FacilityConfiguration findByFacilityEmail(String facilityEmail) {
        return facilityConfigurationRepository.findByEmail(facilityEmail);
    }

    @Override
    public FacilityConfiguration findByFacilityWebsite(String facilityWebsite) {
        return facilityConfigurationRepository.findByWebsite(facilityWebsite);
    }

    @Override
    public void saveFacility(FacilityConfiguration facilityConfiguration) {
        facilityConfigurationRepository.save(facilityConfiguration);
    }

    @Override
    public Optional<List<FacilityConfiguration>> findAllFacilities() {
        return Optional.of(facilityConfigurationRepository.findAll());
    }

    public void updateProfilePicture(FacilityConfiguration facilityConfiguration, String profilePicture) {
        this.facilityConfigurationRepository.updateProfilePicture(facilityConfiguration.getEmail(), profilePicture);
    }
}
