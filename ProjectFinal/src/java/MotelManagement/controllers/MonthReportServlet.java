package MotelManagement.controllers;

import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ReportData;
import MotelManagement.dto.Room;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MonthReportServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));
        
        List<ReportData> table = new ArrayList<>();
        
        RoomBus roomBus = new RoomBus();
        
        List<Room> rooms = roomBus.getRentedRooms();
        for (Room room : rooms) {
            
            String roomId = room.getId();
            ReportData data = new ReportData();
            data.setMonth(month);
            data.setRoomName(room.getName());
            data.setMoney(roomBus.getSumMoneyByMonth(roomId, month, year));
            data.setRentedNum(roomBus.getRentedNumberByMonth(roomId, month, year));
            
            table.add(data);
        }
        
        request.setAttribute("table", table);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Report");
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
