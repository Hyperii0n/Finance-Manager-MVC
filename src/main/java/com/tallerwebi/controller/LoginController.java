package com.tallerwebi.controller;

import com.tallerwebi.service.LoginService;
import com.tallerwebi.service.User;
import com.tallerwebi.service.excepcion.ExistingUser;
import com.tallerwebi.service.excepcion.IncorrectUserOrPasswordException;
import com.tallerwebi.controller.dto.UserHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public ModelAndView login() {

        ModelMap model = new ModelMap();
        model.put("loginData", new LoginData());
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/process-login", method = RequestMethod.POST)
    public ModelAndView processLogin (@ModelAttribute("loginData") LoginData loginData, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        try {
            loginService.authenticate(loginData.getEmail(), loginData.getPassword());

            request.getSession().setAttribute("LOGGED_USER_EMAIL", loginData.getEmail());

            return new ModelAndView("redirect:/home");

        } catch (IncorrectUserOrPasswordException e) {
            model.put("error", "Invalid email or password");
            return new ModelAndView("login", model);
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") User user) {
        ModelMap model = new ModelMap();
        try{
            loginService.register(user);
        } catch (ExistingUser e){
            model.put("error", "User already exists");
            return new ModelAndView("new-user", model);
        } catch (Exception e){
            model.put("error", "Error during registration");
            return new ModelAndView("new-user", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/new-user", method = RequestMethod.GET)
    public ModelAndView newUser() {
        ModelMap model = new ModelMap();
        model.put("user", new User());
        return new ModelAndView("new-user", model);
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        String loggedEmail = (String) request.getSession().getAttribute("LOGGED_USER_EMAIL");
        if(loggedEmail == null){
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();
        UserHeaderDto userHeaderDto = this.loginService.getUserHeader(loggedEmail);
        model.put("user", userHeaderDto);


        return new ModelAndView("home",model);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("redirect:/login");
    }
}

