package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.dtos.UserDTO;
import com.gerstox.projects.motostore_backend.entities.Role;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.entities.UserProfile;
import com.gerstox.projects.motostore_backend.repositories.RoleRepository;
import com.gerstox.projects.motostore_backend.repositories.UserProfileRepository;
import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.services.UserService;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private static final String ROLE_USER = "ROLE_USER";

  @Autowired private UserRepository userRepository;

  @Autowired private UserProfileRepository profileRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<User> findAll(Integer page, Integer elements) {
    Pageable pageRequest = PageRequest.of(page, elements);
    return userRepository.findAll(pageRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Transactional
  @Override
  public User save(UserDTO userDto) {

    Role role = roleRepository.findByName(ROLE_USER).orElseThrow();

    User user =
        User.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .roles(Set.of(role))
            .build();

    User userDB = userRepository.save(user);

    UserProfile profile =
        UserProfile.builder()
            .email(userDto.getEmail())
            .name(userDto.getName())
            .identificationCard(userDto.getIdentification())
            .phone(userDto.getPhone())
            .country(userDto.getCountry())
            .state(userDto.getState())
            .city(userDto.getCity())
            .user(user)
            .build();

    profileRepository.save(profile);

    System.out.println(userDto);
    return userDB;
  }

  @Override
  public Optional<User> update(Long id, User user) {
    // Optional<User> userOptional = userRepository.findById(id);

    // if (userOptional.isEmpty()) {
    //   System.out.println("ERROR: El servicio no existe.");
    // }

    // User userDB = userOptional.orElseThrow();
    // userDB =
    // User.builder()
    //         .name(user.getName())
    //         .image(user.getImage())
    //         .description(user.getDescription())
    //         .duration(user.getDuration())
    //         .price(user.getPrice())
    //         .build();
    // TO DO
    return Optional.of(new User());
  }

  @Transactional
  @Override
  public Optional<User> delete(Long id) {
    Optional<User> userOptional = userRepository.findById(id);

    userOptional.ifPresent(
        userDB -> {
          userRepository.delete(userDB);
        });

    return userOptional;
  }

  @Transactional(readOnly = true)
  @Override
  public Boolean existsById(Long id) {
    return userRepository.existsById(id);
  }
}
