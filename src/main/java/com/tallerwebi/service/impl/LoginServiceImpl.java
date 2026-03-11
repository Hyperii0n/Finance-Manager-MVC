package com.tallerwebi.service.impl;

import com.tallerwebi.model.User;
import com.tallerwebi.repository.UserRepository;
import com.tallerwebi.service.LoginService;
import com.tallerwebi.exception.IncorrectUserOrPasswordException;
import com.tallerwebi.exception.ExistingUser;
import com.tallerwebi.dto.UserHeaderDto;
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
    public User authenticate(String email, String password) throws IncorrectUserOrPasswordException {
       User user = userRepository.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)) throw new IncorrectUserOrPasswordException();
        return user;
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
    public UserHeaderDto getUserHeader(Long id) {
        User user = this.userRepository.findById(id);
        return  new UserHeaderDto(user.getName(),user.getPhotoUrl());
    }

}

