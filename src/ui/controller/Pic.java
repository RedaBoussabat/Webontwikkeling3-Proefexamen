package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Pic extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picKey = request.getParameter("pic");
        Cookie cookie = new Cookie("picKey", picKey);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        request.setAttribute("picKey", picKey);
        forward("index.jsp", request, response);
    }
}
