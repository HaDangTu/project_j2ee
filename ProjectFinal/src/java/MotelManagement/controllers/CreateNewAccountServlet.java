package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoleBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Role;
import MotelManagement.util.Generator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateNewAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //        HttpSession session = request.getSession();
//        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
//        
        RequestDispatcher dispatcher;
//        if (user != null) {
//            AccountBus accountBus = new AccountBus();
//            String role = accountBus.getRole(user);
//            
//            if (role.equals(Constant.OWNER)) {
                    RoleBus roleBus = new RoleBus();
                    List<Role> roles = roleBus.getAll();
                    
                    request.setAttribute("roles", roles);
                    String path = "WEB-INF/views/account/create.jsp";
                    dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);
//            }
//            else {
//                dispatcher = request.getRequestDispatcher("/Login");
//            }
//        }
//        else {
//            dispatcher = request.getRequestDispatcher("/Login");
//        }
//        dispatcher.forward(request, response);                    
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("re-enter-pass");
        String roleId = request.getParameter("role-select");
        
        AccountBus accountBus = new AccountBus();
        String usernameErrMsg = accountBus.isValidUsername(username);
        String passwordErrMsg = accountBus.isValidPassword(password, confirmPass);
        
        if (!usernameErrMsg.equals("") || !passwordErrMsg.equals("")) { //ERROR
            request.setAttribute("usernameErrMsg", usernameErrMsg);
            request.setAttribute("passErrMsg", passwordErrMsg);
            
            RoleBus roleBus = new RoleBus();
            List<Role> roles = roleBus.getAll();
            
            request.setAttribute("roles", roles);
            String path = "WEB-INF/views/account/create.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } else {
            ApplicationUser account = new ApplicationUser();
            account.setUsername(username);
            account.setPassword(Generator.hashPassword(password));
            account.setRoleId(roleId);
            
            accountBus.insert(account);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListAccount");
            dispatcher.forward(request, response);
        }
    }
}
