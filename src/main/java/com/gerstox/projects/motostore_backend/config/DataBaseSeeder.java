package com.gerstox.projects.motostore_backend.config;

import com.gerstox.projects.motostore_backend.entities.Role;
import com.gerstox.projects.motostore_backend.repositories.RoleRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataBaseSeeder {

  private final JdbcTemplate jdbcTemplate;
  private final RoleRepository roleRepository;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedRoleTable();
  }

  private void seedRoleTable() {
    String sql = "SELECT * FROM roles LIMIT 1";
    List<Role> exists = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
    if (exists == null || exists.size() <= 0) {

      List<Role> roles =
          Arrays.asList(
              Role.builder().name("ROLE_USER").build(),
              Role.builder().name("ROLE_RESELLER").build(),
              Role.builder().name("ROLE_ADMIN").build());

      roleRepository.saveAll(roles);

      log.info("Seeding roles...");
    } else {
      log.trace("Roles Seeding Not Required");
    }
  }
}
