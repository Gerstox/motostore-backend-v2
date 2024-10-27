package com.gerstox.projects.motostore_backend.repositories;

import com.gerstox.projects.motostore_backend.entities.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);
}
