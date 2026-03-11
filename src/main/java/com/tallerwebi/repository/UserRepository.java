package com.tallerwebi.repository;

import com.tallerwebi.model.User;

public interface UserRepository {

    void save(User user);
    User findById(Long id);
    User findByEmail(String email);
}

