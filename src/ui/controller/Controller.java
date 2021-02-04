package ui.controller;

import domain.model.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;



@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static boolean sendRedirect = false;

    private ProductService productService;
    private ControllerFactory controllerFactory = new ControllerFactory();

    public Controller() {
        super();
    }

    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();

        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }

        productService = new ProductService(properties);
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(request.getParameter("action") == null) {
            action = "home";
        }

        try {
            RequestHandler handler = controllerFactory.getController(action, productService);
            handler.handleRequest(request, response);
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }



}
