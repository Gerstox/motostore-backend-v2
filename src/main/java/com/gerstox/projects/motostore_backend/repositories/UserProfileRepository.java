package com.gerstox.projects.motostore_backend.repositories;

import com.gerstox.projects.motostore_backend.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
}
