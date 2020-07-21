package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.dto.ApplicationUser;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = "WEB-INF/views/account/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountBus accountBus = new AccountBus();

        ApplicationUser user = accountBus.getUser(username);
        String errMessage = "Wrong username or password";

        RequestDispatcher dispatcher;
        if (user != null) {
            if (accountBus.isValid(user, password)) {
                dispatcher = request.getRequestDispatcher("/Home");
                session.setAttribute("user", user);
            } else {
                request.setAttribute("errMsg", errMessage);
                dispatcher = request.getRequestDispatcher("WEB-INF/views/account/login.jsp");
            }
        } else {
            request.setAttribute("errMsg", errMessage);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/account/login.jsp");
        }

        dispatcher.forward(request, response);
    }
}
