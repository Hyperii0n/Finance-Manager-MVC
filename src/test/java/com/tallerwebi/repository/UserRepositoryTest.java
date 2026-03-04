package com.tallerwebi.repository;

import com.tallerwebi.service.User;
import com.tallerwebi.service.interfaces.UserRepository;
import com.tallerwebi.integration.config.HibernateTestConfig;
import com.tallerwebi.integration.config.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenExistingUser_whenFindByEmail_thenReturnUser() {
        User user = new User("franco@gmail.com","franco","aaaa1111","s");

        this.userRepository.save(user);
        User userFound = this.userRepository.findByEmail(user.getEmail());

        assertNotNull(userFound.getId());
        assertEquals(user.getEmail(), userFound.getEmail());
    }

    @Test
    public void givenExistingUser_whenFindByEmail_thenReturnCorrectPhotoUrl() {
        User user = new User("franco@gmail.com","franco","aaaa1111","s");

        this.userRepository.save(user);
        User userFound = this.userRepository.findByEmail(user.getEmail());

        assertNotNull(userFound.getId());
        assertEquals(user.getEmail(), userFound.getEmail());
        assertEquals(user.getPhotoUrl(), userFound.getPhotoUrl());
    }
}
