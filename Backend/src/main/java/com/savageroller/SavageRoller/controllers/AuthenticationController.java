package com.savageroller.SavageRoller.controllers;

import com.savageroller.SavageRoller.data.UserRepository;
import com.savageroller.SavageRoller.models.User;
import com.savageroller.SavageRoller.models.dto.LoginFormDTO;
import com.savageroller.SavageRoller.models.dto.RegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session){

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if(userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return null;
        }
        return user.get();
    }
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
    @GetMapping("/register")
    public String displayRegistrationForm(Model model){
        model.addAttribute(new RegistrationDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
