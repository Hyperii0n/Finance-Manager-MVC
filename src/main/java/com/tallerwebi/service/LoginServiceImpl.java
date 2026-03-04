package com.tallerwebi.service;

import com.tallerwebi.service.excepcion.IncorrectUserOrPasswordException;
import com.tallerwebi.service.excepcion.ExistingUser;
import com.tallerwebi.service.interfaces.UserRepository;
import com.tallerwebi.controller.dto.UserHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void authenticate(String email, String password) throws IncorrectUserOrPasswordException {
       User user = userRepository.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)) throw new IncorrectUserOrPasswordException();
    }

    @Override
    public void register(User usuario) throws ExistingUser {
        User userFound = userRepository.findByEmail(usuario.getEmail());
        if(userFound != null){
            throw new ExistingUser();
        }
        userRepository.save(usuario);
    }

    @Override
    public UserHeaderDto getUserHeader(String email) {
        User user = this.userRepository.findByEmail(email);
        return  new UserHeaderDto(user.getName(),user.getPhotoUrl());
    }

}

