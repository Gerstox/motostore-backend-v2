package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }
}
