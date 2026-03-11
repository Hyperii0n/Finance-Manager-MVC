package com.tallerwebi.repository;

import com.tallerwebi.model.User;
import com.tallerwebi.integration.HibernateTestConfig;
import com.tallerwebi.integration.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenExistingUser_whenSaveOther_thenBothHaveDistinctIds() {
        User user1 = new User("alice@email.com","test","Alice","x");
        User user2 = new User("homer@email.com","test","Homer Simpson","x");

        this.userRepository.save(user1);
        this.userRepository.save(user2);

        assertNotNull(user1.getId());
        assertNotNull(user2.getId());

        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    public void givenExistingUser_whenFindById_thenReturnUser(){
        User user = new User("alice@email.com","test","Alice","x");

        this.userRepository.save(user);
        User userFound = this.userRepository.findById(user.getId());

        assertNotNull(userFound.getId());
        assertEquals(user.getId(), userFound.getId());
    }

    @Test
    public void givenExistingUser_whenFindByEmail_thenReturnUser() {
        User user = new User("alice@email.com","test","Alice","x");

        this.userRepository.save(user);
        User userFound = this.userRepository.findByEmail(user.getEmail());

        assertNotNull(userFound.getId());
        assertEquals(user.getEmail(), userFound.getEmail());
    }
}
