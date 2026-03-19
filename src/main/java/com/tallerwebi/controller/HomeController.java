package com.tallerwebi.controller;

import com.tallerwebi.dto.UserHeaderDto;
import com.tallerwebi.model.Operation;
import com.tallerwebi.service.LoginService;
import com.tallerwebi.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    private LoginService loginService;
    private OperationService operationService;

    @Autowired
    public HomeController(LoginService loginService, OperationService operationService) {
        this.loginService = loginService;
        this.operationService = operationService;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        Long loggedId = (Long) request.getSession().getAttribute("LOGGED_USER_ID");

        if(loggedId == null){
            return new ModelAndView("redirect:/login");
        }

        ModelMap model = new ModelMap();

        UserHeaderDto userHeaderDto = this.loginService.getUserHeader(loggedId);
        model.put("user", userHeaderDto);

        List<Operation> recentOperations = this.operationService.getRecentOperationsByUserId(loggedId);
        model.put("operations", recentOperations);

        return new ModelAndView("home", model);
    }
}