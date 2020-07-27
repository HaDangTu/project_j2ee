package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Room;
import MotelManagement.util.Constant;
import MotelManagement.util.Generator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        if (user != null) {
            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);

            if (role.equals(Constant.OWNER)) {
                String userId = request.getParameter("id");
                ApplicationUser resetUser = accountBus.getUserById(userId);
                
                role = accountBus.getRole(resetUser);
                String resetPass = "";
                
                if (role.equals(Constant.OWNER)) {
                    resetPass = Generator.generateAdminPass();
                }
                else if (role.equals(Constant.GUEST)) {
                    RoomBus roomBus = new RoomBus();
                    Room room = roomBus.getRoom(resetUser);
                    resetPass = Generator.generateGuestPass(room.getName());
                }
                
                resetUser.setPassword(Generator.hashPassword(resetPass));
                accountBus.changePassword(resetUser);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListAccount");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
