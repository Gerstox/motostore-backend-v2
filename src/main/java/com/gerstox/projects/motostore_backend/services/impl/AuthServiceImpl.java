package com.gerstox.projects.motostore_backend.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerstox.projects.motostore_backend.dtos.UserRegisterDTO;
import com.gerstox.projects.motostore_backend.entities.Role;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.entities.UserProfile;
import com.gerstox.projects.motostore_backend.repositories.RoleRepository;
import com.gerstox.projects.motostore_backend.repositories.UserProfileRepository;
import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(UserRegisterDTO userRegister) {

        Role role = roleRepository.findByName(ROLE_USER).orElseThrow();

        User user = User
        .builder()
        .username(userRegister.getUsername())
        .password(passwordEncoder.encode( userRegister.getPassword()))
        .roles(Set.of(role))
        .build();

        User userDB = userRepository.save(user);

        UserProfile profile = UserProfile
        .builder()
        .email(userRegister.getEmail())
        .name(userRegister.getName())
        .identificationCard(userRegister.getIdentification())
        .phone(userRegister.getPhone())
        .country(userRegister.getCountry())
        .state(userRegister.getState())
        .city(userRegister.getCity())
        .user(user)
        .build();

        profileRepository.save(profile);

        System.out.println(userRegister);
        return userDB;
    }

}
