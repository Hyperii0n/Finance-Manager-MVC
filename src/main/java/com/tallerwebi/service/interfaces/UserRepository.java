package com.tallerwebi.service.interfaces;

import com.tallerwebi.service.User;

public interface UserRepository {

    User findByEmailAndPassword(String email, String password);
    void save(User usuario);
    User findByEmail(String email);
    void modify(User usuario);
    String findPhotoByEmail(String email);
}

