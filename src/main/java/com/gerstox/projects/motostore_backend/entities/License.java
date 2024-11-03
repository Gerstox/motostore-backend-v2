package com.gerstox.projects.motostore_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "licenses")
public class License {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty
  @Column(nullable = false)
  private String name;

  private String description;
  private String image;
  private Integer duration;
  
  @NotNull
  @Column(nullable = false)
  private Double price;

  @NotNull
  @Column(nullable = false, columnDefinition = "bit(1) default 1")
  private Boolean status;
}
