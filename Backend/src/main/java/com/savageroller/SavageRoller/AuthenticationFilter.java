package com.savageroller.SavageRoller;

import com.savageroller.SavageRoller.controllers.AuthenticationController;
import com.savageroller.SavageRoller.data.UserRepository;
import com.savageroller.SavageRoller.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController  authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{

        if(isWhitelisted(request.getRequestURI())) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if (user != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }

    private static boolean isWhitelisted (String path) {
        for(String pathRoot : whitelist) {
            if(path.equals(pathRoot)) {
                return true;
            }
        }
        return false;
    }

}
