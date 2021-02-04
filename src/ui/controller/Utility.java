package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utility {
    public static void CheckRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            for (Role role : roles){
                if (user.getRole().equals(role)){
                    found = true;
                }
            }
        }
        if (!found){
            throw new NotAuthorizedException();
        }
    }
}
