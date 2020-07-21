package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoleBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.AccountInfo;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Role;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");

        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);

            if (role.equals(Constant.OWNER)) {
                RoleBus roleBus = new RoleBus();

                List<Role> roles = roleBus.getAll();
                String id = request.getParameter("id");

                ApplicationUser account = accountBus.getUserById(id);
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setId(account.getId());
                accountInfo.setUsername(account.getUsername());
                accountInfo.setPassword(account.getPassword());
                accountInfo.setRole(accountBus.getRole(account));

                request.setAttribute("account", accountInfo);
                request.setAttribute("roles", roles);

                String path = "WEB-INF/views/account/delete.jsp";
                dispatcher = request.getRequestDispatcher(path);

            } else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        } else {
            dispatcher = request.getRequestDispatcher("/Login");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        AccountBus accountBus = new AccountBus();
        ApplicationUser user = new ApplicationUser();
        user.setId(id);

        accountBus.delete(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListAccount");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
