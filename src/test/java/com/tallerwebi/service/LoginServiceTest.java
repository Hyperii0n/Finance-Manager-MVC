package com.tallerwebi.service;


import com.tallerwebi.service.excepcion.ExistingUser;
import com.tallerwebi.service.excepcion.IncorrectUserOrPasswordException;
import com.tallerwebi.service.interfaces.UserRepository;
import com.tallerwebi.controller.dto.UserHeaderDto;
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
        User user = new User("franco@gmail.com","franco","aaaa1111","s");
        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(null);

        assertThrows(IncorrectUserOrPasswordException.class, () -> {
            this.loginService.authenticate(user.getEmail(), user.getPassword());
        });
    };

    @Test
    public void givenExistingUser_whenAunthenticateIncorrectPassword_thenThrowsIncorrectUserOrPasswordException(){
        User user = new User("franco@gmail.com","franco","aaaa1111", "s");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(IncorrectUserOrPasswordException.class, () -> {
           this.loginService.authenticate(user.getEmail(), "bbbb2222");
        });
    }

    @Test
    public void givenNonExistentUser_whenRegister_thenCallRepositoryMethod() throws ExistingUser {
        User user = new User("franco@gmail.com","franco","aaaa1111", "s");
        this.loginService.register(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void givenExistingUser_whenRegister_thenThrowsExistingUserException() throws ExistingUser {
        User user = new User("franco@gmail.com","franco","aaaa1111", "s");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThrows(ExistingUser.class, () -> {
            this.loginService.register(user);
        });
    }

    @Test
    public void givenExistingUser_whenGetUserHeaderDto_thenReturnUserHeaderWithCorrectData(){
        User user = new User("franco@gmail.com","franco","aaaa1111", "s");
        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        UserHeaderDto userHeaderDto = this.loginService.getUserHeader(user.getEmail());

        assertEquals(user.getName(), userHeaderDto.getName());
        assertEquals(user.getPhotoUrl(), userHeaderDto.getPhotoUrl());
    }

    @Test
    public void givenExistingUser_whenAuthenticate_thenDoesNotThrowException() throws IncorrectUserOrPasswordException {
        User user = new User("franco@gmail.com","franco","aaaa1111", "s");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(user);

        this.loginService.authenticate(user.getEmail(), user.getPassword());
    }

}
