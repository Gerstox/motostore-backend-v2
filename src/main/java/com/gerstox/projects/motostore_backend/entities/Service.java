package com.gerstox.projects.motostore_backend.entities;

import com.gerstox.projects.motostore_backend.enums.ServiceType;
import com.gerstox.projects.motostore_backend.validators.ValidEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "services")
@Inheritance(strategy = InheritanceType.JOINED)
public class Service {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotNull
  @Column(name = "service_type")
  @Enumerated(EnumType.STRING)
  @ValidEnum(
      enumClass = ServiceType.class,
      message = "El tipo de servicio debe ser uno de: {enumValues}")
  protected ServiceType serviceType;

  @NotEmpty
  @Column(nullable = false)
  protected String name;

  @Column(nullable = true)
  protected String description;

  @Column(nullable = true)
  protected String image;

  @NotNull
  @Column(nullable = false, columnDefinition = "bit(1) default 1")
  protected Boolean status;

  protected Service() {}

  protected Service(
      Long id,
      ServiceType serviceType,
      String name,
      String description,
      String image,
      Boolean status) {
    this.id = id;
    this.serviceType = serviceType;
    this.name = name;
    this.description = description;
    this.image = image;
    this.status = status;
  }
}