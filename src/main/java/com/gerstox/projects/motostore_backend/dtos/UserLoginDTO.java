package com.gerstox.projects.motostore_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLoginDTO {
  @NotBlank(message = "{NotBlank.login.username}")
  private String username;

  @NotBlank(message = "{NotBlank.login.password}")
  private String password;
}
