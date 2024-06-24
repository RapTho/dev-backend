// src/main/java/com/example/service/UserService.java
package com.example.service;

import com.example.model.AppUser;
import com.example.repository.AppUserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AppUserService {

    @Inject
    AppUserRepository userRepository;

    public List<AppUser> listAllUsers() {
        return userRepository.listAll();
    }

    public AppUser findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public AppUser createUser(AppUser user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public AppUser updateUser(Long id, AppUser user) {
        AppUser existingUser = userRepository.findById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return existingUser;
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
