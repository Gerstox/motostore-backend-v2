package com.gerstox.projects.motostore_backend.dtos;

import com.gerstox.projects.motostore_backend.annotations.IsUnique;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterDTO {

  @NotBlank(message = "{NotBlank.register.username}")
  @IsUnique
  private String username;

  @NotBlank(message = "{NotBlank.register.password}")
  private String password;

  @NotBlank(message = "{NotBlank.register.name}")
  private String name;

  @NotBlank(message = "{NotBlank.register.identification}")
  private String identification;

  @Email(message = "{Email.register.email}")
  private String email;
  private String phone;
  private String country;
  private String state;
  private String city;
}
