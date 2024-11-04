package com.gerstox.projects.motostore_backend.services;

import com.gerstox.projects.motostore_backend.entities.License;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface LicenseService {

  public Page<License> findAll(Integer page, Integer elements);

  public Optional<License> findById(Long id);

  public License save(License license);

  public Optional<License> update(Long id, License license);

  public Optional<License> delete(Long id);

  public Boolean existsById(Long id);
}
