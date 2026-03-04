package com.tallerwebi.service;

import com.tallerwebi.service.excepcion.IncorrectUserOrPasswordException;
import com.tallerwebi.service.excepcion.ExistingUser;
import com.tallerwebi.controller.dto.UserHeaderDto;

public interface LoginService {

    void authenticate(String email, String password) throws IncorrectUserOrPasswordException;
    void register(User usuario) throws ExistingUser;
    UserHeaderDto getUserHeader(String email) ;
}
