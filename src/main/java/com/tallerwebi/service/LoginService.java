package com.tallerwebi.service;

import com.tallerwebi.model.User;
import com.tallerwebi.exception.IncorrectUserOrPasswordException;
import com.tallerwebi.exception.ExistingUser;
import com.tallerwebi.dto.UserHeaderDto;

public interface LoginService {

    User authenticate(String email, String password) throws IncorrectUserOrPasswordException;
    void register(User usuario) throws ExistingUser;
    UserHeaderDto getUserHeader(Long id) ;
}
