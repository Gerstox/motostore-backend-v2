package com.gerstox.projects.motostore_backend.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private Boolean enabled;

  @Transient private boolean admin;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<Role> roles;

  @PrePersist
  public void prePersist() {
    if (enabled == null) {
      enabled = true;
    }
  }

  @Override
  public String toString() {
    return "{ id="
        + id
        + ", username="
        + username
        + ", password="
        + password
        + ", enabled="
        + enabled
        + ", admin="
        + admin
        + ", roles="
        + roles
        + " }";
  }
}
