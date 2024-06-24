// src/main/java/com/example/service/UserService.java
package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> getAllUsers(int page, int size, String sortKey) {
        return userRepository.findAll(Sort.by(sortKey))
                .page(page, size)
                .list();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id);

        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAge(updatedUser.getAge());
            userRepository.persist(user);
        }
        return user;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
