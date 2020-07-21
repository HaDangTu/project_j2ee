package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomInfo;
import MotelManagement.dto.RoomType;
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

public class ListRoomServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");

        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();

            String role = accountBus.getRole(user);

            if (role.equals(Constant.OWNER)) {
                RoomBus roomBus = new RoomBus();
                RoomTypeBus roomTypeBus = new RoomTypeBus();
                
                List<Room> rooms = roomBus.getAll();
                List<RoomInfo> roomInfos = new ArrayList<>();
                for (Room room : rooms) {
                    RoomInfo roomInfo = new RoomInfo();
                    RoomType roomType = roomTypeBus.getRoomType(room.getRoomTypeId());
                    
                    roomInfo.setId(room.getId());
                    roomInfo.setName(room.getName());
                    roomInfo.setRoomType(roomType.getName());
                    roomInfo.setPrice(roomBus.getPrice(room.getId()));
                    roomInfo.setUserId(room.getUserId());
                    
                    roomInfos.add(roomInfo);
                }
                
                request.setAttribute("rooms", roomInfos);
                String path = "WEB-INF/views/room/index.jsp";
                dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
                
            } else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        } else {
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
