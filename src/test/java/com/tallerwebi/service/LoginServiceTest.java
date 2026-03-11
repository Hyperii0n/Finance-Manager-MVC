package com.tallerwebi.service;


import com.tallerwebi.model.User;
import com.tallerwebi.repository.UserRepository;
import com.tallerwebi.exception.ExistingUser;
import com.tallerwebi.exception.IncorrectUserOrPasswordException;
import com.tallerwebi.dto.UserHeaderDto;
import com.tallerwebi.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class LoginServiceTest {
    private UserRepository userRepository;
    private LoginService loginService;

    @BeforeEach
    public void setUp(){
        userRepository =  mock(UserRepository.class);
        loginService = new LoginServiceImpl(userRepository);
    }

    @Test
    public void givenNonExistentUser_whenAuthenticate_thenThrowsIncorrectUserOrPasswordException() throws IncorrectUserOrPasswordException {
        User user = new User("alice@email.com","test","Alice","x");
        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(null);

        assertThrows(IncorrectUserOrPasswordException.class, () -> {
            this.loginService.authenticate(user.getEmail(), user.getPassword());
        });
    };

    @Test
    public void givenExistingUser_whenAunthenticateIncorrectPassword_thenThrowsIncorrectUserOrPasswordException(){
        User user = new User("alice@email.com","test","Alice","x");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(IncorrectUserOrPasswordException.class, () -> {
           this.loginService.authenticate(user.getEmail(), "a1s2d3");
        });
    }

    @Test
    public void givenNonExistentUser_whenRegister_thenCallRepositoryMethod() throws ExistingUser {
        User user = new User("alice@email.com","test","Alice","x");
        this.loginService.register(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void givenExistingUser_whenRegister_thenThrowsExistingUserException() throws ExistingUser {
        User user = new User("alice@email.com","test","Alice","x");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(ExistingUser.class, () -> {
            this.loginService.register(user);
        });
    }

    @Test
    public void givenExistingUser_whenGetUserHeaderDto_thenReturnUserHeaderWithCorrectData(){
        User user = new User("alice@email.com","test","Alice","x");
        when(this.userRepository.findById(user.getId())).thenReturn(user);

        UserHeaderDto userHeaderDto = this.loginService.getUserHeader(user.getId());

        assertEquals(user.getName(), userHeaderDto.getName());
        assertEquals(user.getPhotoUrl(), userHeaderDto.getPhotoUrl());
    }

    @Test
    public void givenExistingUser_whenAuthenticate_thenDoesNotThrowException() throws IncorrectUserOrPasswordException {
        User user = new User("alice@email.com","test","Alice","x");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        this.loginService.authenticate(user.getEmail(), user.getPassword());
    }

}
