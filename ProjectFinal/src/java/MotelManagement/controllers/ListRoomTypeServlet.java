
package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.RoomType;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ListRoomTypeServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        
        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);
            
            if (role.equals(Constant.OWNER)) {
                RoomTypeBus roomTypeBus = new RoomTypeBus();
                List<RoomType> roomTypes = roomTypeBus.getAll();
                
                request.setAttribute("roomTypes", roomTypes);
                String path = "WEB-INF/views/room_type/index.jsp";
                
                dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            }
            else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        }
        else {
            dispatcher = request.getRequestDispatcher("/Login");
        }
        dispatcher.forward(request, response);
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
