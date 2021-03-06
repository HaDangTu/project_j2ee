
package MotelManagement.controllers;

import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ReportData;
import MotelManagement.dto.Room;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdvanceReportServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromDateStr = request.getParameter("from-date");
        String toDateStr = request.getParameter("to-date");
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fromDate = formatter.parse(fromDateStr);
            Date toDate = formatter.parse(toDateStr);
            
            List<ReportData> table = new ArrayList<>();
            
            RoomBus roomBus = new RoomBus();
            List<Room> rooms = roomBus.getRentedRooms();
            
            roomBus.getRentedRooms();
            for (Room room : rooms) {
                String roomId = room.getId();
                ReportData data = new ReportData();
                data.setRoomName(room.getName());
                data.setRentedNum(roomBus.getRentedNumberByDate(roomId, fromDate, toDate));
                data.setMoney(roomBus.getSumMoneyByDate(roomId, fromDate, toDate));
                
                table.add(data);
            }
            
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            String reportTitle = "Báo cáo doanh thu";
            String reportSub = "Từ ngày " + formatter.format(fromDate) + "\tĐến ngày " + formatter.format(toDate);
            
            request.setAttribute("table", table);
            request.setAttribute("reportTitle", reportTitle);
            request.setAttribute("reportSub", reportSub);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Report");
            dispatcher.forward(request, response);
        }
        catch (ParseException e) {
            System.err.println(e.getMessage());
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
