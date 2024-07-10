// src/main/java/com/example/service/UserService.java
package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import io.quarkus.panache.common.Sort;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @WithSession
    public Uni<List<User>> getAllUsers(int page, int size, String sortKey) {
        return userRepository.findAll(Sort.by(sortKey))
                .page(page, size)
                .list();
    }

    @WithSession

    public Uni<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @WithTransaction
    public Uni<User> createUser(User user) {
        return userRepository.persist(user);
    }

    @WithTransaction
    public Uni<User> updateUser(Long id, User updatedUser) {

        return userRepository.findById(id)
                .onItem().ifNotNull().transformToUni(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setAge(updatedUser.getAge());
                    return userRepository.persist(user)
                            .replaceWith(user);
                })
                .onItem().ifNull().continueWith(() -> null);
    }

    @WithTransaction
    public Uni<Boolean> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
