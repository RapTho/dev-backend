// src/main/java/com/example/repository/UserRepository.java
package com.example.repository;

import com.example.model.AppUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppUserRepository implements PanacheRepository<AppUser> {
}
