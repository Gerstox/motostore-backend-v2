package com.gerstox.projects.motostore_backend.config;

import com.gerstox.projects.motostore_backend.entities.Role;
import com.gerstox.projects.motostore_backend.entities.User;
import com.gerstox.projects.motostore_backend.entities.UserProfile;
import com.gerstox.projects.motostore_backend.repositories.RoleRepository;
import com.gerstox.projects.motostore_backend.repositories.UserRepository;
import com.gerstox.projects.motostore_backend.repositories.UserProfileRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataBaseSeeder {

  private final JdbcTemplate jdbcTemplate;
  private final PasswordEncoder passwordEncoder;
  
  private final UserRepository userRepository;
  private final UserProfileRepository userProfileRepository;
  private final RoleRepository roleRepository;

  private static final String ROLE_USER = "ROLE_USER";
  private static final String ROLE_RESELLER = "ROLE_RESELLER";
  private static final String ROLE_ADMIN = "ROLE_ADMIN";


  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedRoleTable();
    seedAdminUser("admin", ROLE_ADMIN, "0");
    seedAdminUser("reseller", ROLE_RESELLER, "1");
  }

  private void seedRoleTable() {
    String sql = "SELECT * FROM roles LIMIT 1";
    List<Role> exists = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
    if (exists == null || exists.size() <= 0) {

      List<Role> roles =
          Arrays.asList(
              Role.builder().name(ROLE_USER).build(),
              Role.builder().name(ROLE_RESELLER).build(),
              Role.builder().name(ROLE_ADMIN).build());

      roleRepository.saveAll(roles);

      log.info("Seeding roles...");
    } else {
      log.trace("Roles Seeding Not Required");
    }
  }

  private void seedAdminUser(String username, String roleName, String identificationCard) {
    String sql = String.format("SELECT * FROM users u WHERE u.username = \"%s\" LIMIT 1", username);
    List<User> user = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

    if (user == null || user.size() <= 0) {

      Role role = roleRepository.findByName(roleName).orElseThrow();
      Role roleUser = roleRepository.findByName(ROLE_USER).orElseThrow();

      User userSeed = User
        .builder()
        .username(username)
        .password(passwordEncoder.encode(String.format("%s123456", username)))
        .roles(Set.of(role, roleUser))
        .build();

        userRepository.save(userSeed);

      UserProfile profileSeed = UserProfile
        .builder()
        .email(String.format("%s@motostore.com", username))
        .name(username)
        .identificationCard(identificationCard)
        .user(userSeed)
        .build();

        userProfileRepository.save(profileSeed);
      log.info(String.format("Seeding User %s", username));
    } else {
      log.trace("Users Seeding Not Required");
    }
  }
}
