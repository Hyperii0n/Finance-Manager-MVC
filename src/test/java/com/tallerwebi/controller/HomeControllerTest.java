package com.tallerwebi.controller;

import com.tallerwebi.dto.UserHeaderDto;
import com.tallerwebi.model.Operation;
import com.tallerwebi.service.LoginService;
import com.tallerwebi.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class HomeControllerTest {

    private HomeController homeController;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private LoginService loginServiceMock;
    private OperationService operationServiceMock;
    private UserHeaderDto userHeaderDto;
    private List<Operation> operationsList;


    @BeforeEach
    public void setUp(){
        loginServiceMock = mock(LoginService.class);
        operationServiceMock = mock(OperationService.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        userHeaderDto = new UserHeaderDto("alice","photo.jpg");
        homeController = new HomeController(loginServiceMock, operationServiceMock);
        operationsList = new ArrayList<>();
    }

    @Test
    public void givenLoggedUser_whenGoesToHome_thenReturnHomePage() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("LOGGED_USER_ID")).thenReturn(1L);

        when(loginServiceMock.getUserHeader(1L)).thenReturn(userHeaderDto);

        when(this.operationServiceMock.getRecentOperationsByUserId(1L)).thenReturn(operationsList);

        ModelAndView modelAndView = homeController.home(requestMock);

        verify(loginServiceMock, times(1)).getUserHeader(1L);
        verify(operationServiceMock, times(1)).getRecentOperationsByUserId(1L);
        assertThat(modelAndView.getModel().get("user"), equalTo(userHeaderDto));
        assertThat(modelAndView.getModel().get("operations"), equalTo(operationsList));
        assertThat(modelAndView.getViewName(), equalTo("home"));
    }

    @Test
    public void givenNotLoggedInUser_whenGoesToHome_thenReturnLoginPage() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("LOGGED_USER_ID")).thenReturn(null);

        ModelAndView modelAndView = homeController.home(requestMock);

        verify(loginServiceMock, never()).getUserHeader(any());
        verify(operationServiceMock, never()).getRecentOperationsByUserId(any());
        assertThat(modelAndView.getViewName(), equalTo("redirect:/login"));
    }
}
