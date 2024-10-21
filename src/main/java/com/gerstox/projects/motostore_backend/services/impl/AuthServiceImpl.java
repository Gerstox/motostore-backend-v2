package com.gerstox.projects.motostore_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerstox.projects.motostore_backend.dtos.UserRegisterDTO;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.entities.UserProfile;
import com.gerstox.projects.motostore_backend.repositories.UserProfileRepository;
import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    @Override
    @Transactional
    public User register(UserRegisterDTO userRegister) {

        User user = User
        .builder()
        .username(userRegister.getUsername())
        .password(userRegister.getPassword())
        .build();

        userRepository.save(user);

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
        return null;
    }

}
