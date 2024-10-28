package com.gerstox.projects.motostore_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "recharges")
public class Recharge {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, columnDefinition = "varchar(100) default 'danlipagos'")
  private String provider;

  @Column(nullable = false)
  private String name;

  @Column(nullable = true)
  private String description;

  @Column(nullable = true)
  private String image;

  @Column(nullable = false, columnDefinition = "bit(1) default 1")
  private Boolean status;
}
