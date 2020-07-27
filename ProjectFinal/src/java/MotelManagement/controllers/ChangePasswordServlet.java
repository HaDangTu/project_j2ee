package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.util.Generator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        if (user != null) {
            String path = "WEB-INF/views/account/change_password.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");

        String oldPass = request.getParameter("old-pass");
        String newPass = request.getParameter("new-pass");
        String confirmPass = request.getParameter("confirm-new-pass");

        String errOldPassMsg = "Vui lòng nhập mật khẩu cũ";
        String errNewPassMsg = "Vui lòng nhập mật khẩu mới";
        String errConfirmPassMsg = "Vui lòng xác nhận mật khẩu";

        if (oldPass.equals("") || newPass.equals("") || confirmPass.equals("")) {
            request.setAttribute("errOldPassMsg", errOldPassMsg);
            request.setAttribute("errNewPassMsg", errNewPassMsg);
            request.setAttribute("errConfirmPassMsg", errConfirmPassMsg);

            doGet(request, response);
            return;
        } else if (!newPass.equals(confirmPass)) {
            request.setAttribute("errConfirmPassMsg", "Mật khẩu không đúng");

            doGet(request, response);
            return;
        } else {
            String oldPassword = user.getPassword();
            oldPass = Generator.hashPassword(oldPass);

            if (oldPassword.equals(oldPass)) {
                ApplicationUser changePassUser = new ApplicationUser();
                changePassUser.setId(user.getId());
                changePassUser.setPassword(Generator.hashPassword(newPass));

                AccountBus accountBus = new AccountBus();
                accountBus.changePassword(changePassUser);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Home");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errOldPassMsg", "Sai mật khẩu");
                doGet(request, response);
                return;
            }
        }
    }

}
