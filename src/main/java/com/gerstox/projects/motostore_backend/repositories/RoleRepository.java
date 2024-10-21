package com.gerstox.projects.motostore_backend.repositories;

import com.gerstox.projects.motostore_backend.entities.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

  Optional<Role> findByName(String name);
}
