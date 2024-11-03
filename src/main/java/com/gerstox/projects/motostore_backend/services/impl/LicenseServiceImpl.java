package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.entities.License;
import com.gerstox.projects.motostore_backend.entities.Recharge;
import com.gerstox.projects.motostore_backend.repositories.LicenseRepository;
import com.gerstox.projects.motostore_backend.services.LicenseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LicenseServiceImpl implements LicenseService {

  @Autowired private LicenseRepository licenseRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<License> findAll(Integer page, Integer elements) {
    Pageable pageRequest = PageRequest.of(page, elements);
    return licenseRepository.findAll(pageRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<License> findById(Integer id) {
    return licenseRepository.findById(id);
  }

  @Transactional
  @Override
  public License save(License license) {
    return licenseRepository.save(license);
  }

  @Transactional
  @Override
  public Optional<License> update(Integer id, License license) {
    Optional<License> licenseOptional = licenseRepository.findById(id);

    if (licenseOptional.isEmpty()) {
      System.out.println("ERROR: El servicio no existe.");
    }

    License licenseDB = licenseOptional.orElseThrow();
    licenseDB =
        License.builder()
            .name(license.getName())
            .description(license.getDescription())
            .image(license.getImage())
            .price(license.getPrice())
            .build();

    return Optional.of(licenseRepository.save(licenseDB));
  }

  @Transactional
  @Override
  public Optional<License> delete(Integer id) {
    Optional<License> licenseOptional = licenseRepository.findById(id);

    licenseOptional.ifPresent(rechargeDB -> {
      licenseRepository.delete(rechargeDB);
    });

    return licenseOptional;
  }

  @Transactional
  @Override
  public Boolean existsById(Integer id) {
    return licenseRepository.existsById(id);
  }
}
