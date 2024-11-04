package com.gerstox.projects.motostore_backend.repositories;

import com.gerstox.projects.motostore_backend.entities.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {}
