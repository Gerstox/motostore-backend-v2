package com.gerstox.projects.motostore_backend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerstox.projects.motostore_backend.dtos.UserLoginDTO;
import com.gerstox.projects.motostore_backend.dtos.UserRegisterDTO;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private AuthService authService;

  @Test
  public void shouldRegisterUser() throws Exception {

    // String userJson =
    //     "{\"username\":\"testuser\", \"password\":\"password\", \"name\":\"name\","
    //         + " \"identification\":\"identification\"}";

    // this.mockMvc
    //
    // .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
    //     .andExpect(status().isOk());

    UserRegisterDTO userDTO =
        UserRegisterDTO.builder()
            .username("username")
            .password("password")
            .name("name")
            .identification("identification")
            .build();

    // Mockear el comportamiento del servicio para evitar interacciones reales con la BD
    when(authService.register(userDTO)).thenReturn(new User());

    mockMvc
        .perform(
            post("/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldResponseWithStatus400WhenDataIsInvalid() throws Exception {

    String userJson = "{\"username\":\"testuser\", \"password\":\"password\", \"name\":\"name\"}";

    this.mockMvc
        .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testLogin() throws Exception {
    UserLoginDTO loginDTO = UserLoginDTO
    .builder()
    .username("username")
    .password("password")
    .build();

    // when(authService.login(loginDTO)).thenReturn(new User());

    mockMvc
        .perform(
            post("/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginDTO)))
        .andExpect(status().isOk());
  }
}
