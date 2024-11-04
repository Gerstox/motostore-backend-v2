package com.gerstox.projects.motostore_backend.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.gerstox.projects.motostore_backend.dtos.UserDTO;
import com.gerstox.projects.motostore_backend.entities.User;

public interface UserService {

  boolean existsByUsername(String username);

  public Page<User> findAll(Integer page, Integer elements);

  public Optional<User> findById(Long id);

  public User save(UserDTO user);

  public Optional<User> update(Long id, User user);

  public Optional<User> delete(Long id);

  public Boolean existsById(Long id);
}
