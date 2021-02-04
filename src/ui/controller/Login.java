package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = Role.valueOf(request.getParameter("role").toUpperCase());
        User user = new User(role.getStringValue(), role);
        request.getSession().setAttribute("user", user);
        redirect("login.jsp", request, response);
    }
}
