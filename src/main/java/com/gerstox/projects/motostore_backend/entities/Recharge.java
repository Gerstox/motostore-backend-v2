package com.gerstox.projects.motostore_backend.entities;

import com.gerstox.projects.motostore_backend.enums.ServiceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "recharges")
public class Recharge extends Service {

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // private Long id;

  @NotEmpty
  @Column(nullable = false, columnDefinition = "varchar(100) default 'danlipagos'")
  private String provider;

  // @NotEmpty
  // @Column(nullable = false)
  // private String name;

  // @Column(nullable = true)
  // private String description;

  // @Column(nullable = true)
  // private String image;

  // @NotNull
  // @Column(nullable = false, columnDefinition = "bit(1) default 1")
  // private Boolean status;

  public Recharge() {
    super();
  }

  public Recharge(
      Long id,
      ServiceType serviceType,
      String name,
      String description,
      String image,
      Boolean status,
      String provider) {
    super(id, serviceType, name, description, image, status);
    this.provider = provider;
  }
}
