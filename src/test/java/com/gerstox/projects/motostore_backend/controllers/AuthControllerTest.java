package com.gerstox.projects.motostore_backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldRegisterUser() throws Exception {

    String userJson = "{\"username\":\"testuser\", \"password\":\"password\", \"name\":\"name\", \"identification\":\"identification\"}";

    this.mockMvc
        .perform(post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(status()
        .isOk());
  }

  @Test
  public void shouldResponseWithStatus400WhenDataIsInvalid() throws Exception {

    String userJson = "{\"username\":\"testuser\", \"password\":\"password\", \"name\":\"name\"}";

    this.mockMvc
        .perform(post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(status()
        .isBadRequest());
  }
}
