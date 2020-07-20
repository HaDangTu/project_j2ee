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


public class EditAccountServlet extends HttpServlet {


    
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
                    AccountBus accountBus = new AccountBus();
                    
                    List<Role> roles = roleBus.getAll();
                    String id = request.getParameter("id");
                    
                    ApplicationUser account = accountBus.getUserById(id);
                    
                    request.setAttribute("account", account);
                    request.setAttribute("roles", roles);
                    
                    String path = "WEB-INF/views/account/edit.jsp";
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
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String roleId = request.getParameter("role-select");
        
        AccountBus accountBus = new AccountBus();
        String usernameErrMsg = accountBus.isValidUsername(username);
        
        if (!usernameErrMsg.equals("")) { //ERROR
            request.setAttribute("usernameErrMsg", usernameErrMsg);
            
            RoleBus roleBus = new RoleBus();
            List<Role> roles = roleBus.getAll();
            
            request.setAttribute("roles", roles);
            String path = "WEB-INF/views/account/create.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } else {
            ApplicationUser account = new ApplicationUser();
            account.setId(id);
            account.setUsername(username);
            account.setRoleId(roleId);
            
            accountBus.update(account);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListAccount");
            dispatcher.forward(request, response);
        }
    }

    

}
