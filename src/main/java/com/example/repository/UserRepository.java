// src/main/java/com/example/repository/UserRepository.java
package com.example.repository;

import com.example.model.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {
}
