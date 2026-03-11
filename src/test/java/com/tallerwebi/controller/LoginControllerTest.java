package com.tallerwebi.controller;

import com.tallerwebi.dto.LoginDataDto;
import com.tallerwebi.service.LoginService;
import com.tallerwebi.model.User;
import com.tallerwebi.exception.ExistingUser;
import com.tallerwebi.exception.IncorrectUserOrPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

	private LoginController loginController;
	private User userMock;
	private LoginDataDto datosLoginMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private LoginService servicioLoginMock;


	@BeforeEach
	public void setUp(){
		datosLoginMock = new LoginDataDto("alice@email.com", "test");
        userMock = mock(User.class);
        when(userMock.getEmail()).thenReturn("alice@email.com");
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioLoginMock = mock(LoginService.class);
		loginController = new LoginController(servicioLoginMock);
	}

	@Test
	public void givenExistingUser_whenProcessLoginWithInvalidUserAndPassword_thenReturnToLoginView() throws IncorrectUserOrPasswordException {

        doThrow(IncorrectUserOrPasswordException.class).when(this.servicioLoginMock).authenticate(anyString(), anyString());
        ModelAndView modelAndView = loginController.processLogin(datosLoginMock, requestMock);

		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Invalid email or password"));
		verify(sessionMock, times(0)).setAttribute(eq("LOGGED_USER_ID"), any());

	};

	@Test
	public void givenExistingUser_whenProcessLoginWithValidCredentials_thenRedirectToHome() throws IncorrectUserOrPasswordException {

		User foundUserMock = mock(User.class);
		when(foundUserMock.getEmail()).thenReturn(datosLoginMock.getEmail());
        when(foundUserMock.getId()).thenReturn(1L);

		when(requestMock.getSession()).thenReturn(sessionMock);
        when(servicioLoginMock.authenticate(anyString(), anyString())).thenReturn(foundUserMock);

		ModelAndView modelAndView = loginController.processLogin(datosLoginMock, requestMock);
		
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/home"));
		verify(sessionMock, times(1)).setAttribute("LOGGED_USER_ID", 1L);
	};

	@Test
	public void givenNonExistentUser_whenRegister_thenRedirectToLogin() throws ExistingUser {

		ModelAndView modelAndView = loginController.register(userMock);

		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
		verify(servicioLoginMock, times(1)).register(userMock);
	}

	@Test
	public void givenExistingUser_whenRegister_thenReturnNewUserViewWithError() throws ExistingUser {

		doThrow(ExistingUser.class).when(servicioLoginMock).register(userMock);

		ModelAndView modelAndView = loginController.register(userMock);

		assertThat(modelAndView.getViewName(), equalToIgnoringCase("new-user"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("User already exists"));
	}

	@Test
	public void givenUnexpectedError_whenRegister_thenReturnNewUserViewWithError() throws ExistingUser {

		doThrow(RuntimeException.class).when(servicioLoginMock).register(userMock);

		ModelAndView modelAndView = loginController.register(userMock);

		assertThat(modelAndView.getViewName(), equalToIgnoringCase("new-user"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error during registration"));
	}
}
