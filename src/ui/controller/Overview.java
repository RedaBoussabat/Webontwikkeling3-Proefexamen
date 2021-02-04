package ui.controller;

import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Overview extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.CUSTOMER, Role.ADMIN};
        Utility.CheckRole(request, roles);
        request.setAttribute("products", getProductService().getAll());
        forward("products.jsp", request, response);
    }
}
