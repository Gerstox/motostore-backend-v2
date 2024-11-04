package com.gerstox.projects.motostore_backend.entities;

import com.gerstox.projects.motostore_backend.enums.ServiceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "streamings")
public class Streaming extends Service {

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // private Long id;

  // @NotBlank
  // @Column(nullable = false)
  // private String name;

  // private String description;
  // private String image;
  private Integer duration;

  @NotNull
  @Column(nullable = false)
  private Double price;

  // @NotNull
  // @Column(nullable = false, columnDefinition = "bit(1) default 1")
  // private Boolean status;

  public Streaming() {
    super();
  }

  public Streaming(
      Long id,
      ServiceType serviceType,
      String name,
      String description,
      String image,
      Boolean status,
      Integer duration,
      Double price) {
    super(id, serviceType, name, description, image, status);
    this.duration = duration;
    this.price = price;
  }
}
