package com.gerstox.projects.motostore_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  @Column(nullable = false)
  private String name;

  @Column(name = "identification_card", nullable = false)
  private String identificationCard;

  private String phone;
  private String country;
  private String state;
  private String city;

  @OneToOne private User user;

  @Override
  public String toString() {
    return "{ id="
        + id
        + ", email="
        + email
        + ", name="
        + name
        + ", identificationCard="
        + identificationCard
        + ", phone="
        + phone
        + ", country="
        + country
        + ", state="
        + state
        + ", city="
        + city
        + " }";
  }
}
