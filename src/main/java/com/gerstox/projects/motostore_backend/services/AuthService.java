package com.gerstox.projects.motostore_backend.services;

import com.gerstox.projects.motostore_backend.dtos.UserRegisterDTO;
import com.gerstox.projects.motostore_backend.entities.User;

public interface AuthService {

    public User register(UserRegisterDTO userRegister);

}
