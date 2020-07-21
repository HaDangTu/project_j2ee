/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotelManagement.controllers;

import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ReportData;
import MotelManagement.dto.Room;
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

/**
 *
 * @author Home
 */
public class QuarterReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] quarters = request.getParameterValues("quarter");
        int year = Integer.valueOf(request.getParameter("year"));

        List<ReportData> table = new ArrayList<>();
        RoomBus roomBus = new RoomBus();
        List<Room> rooms = roomBus.getRentedRooms();

        for (String quarter : quarters) {
            switch (quarter) {
                case Constant.QUARTER_1:
                    for (Room room : rooms) {
                        ReportData reportData = new ReportData();
                        reportData.setRoomName(room.getName());
                        reportData.setRentedNum(roomBus.getRentedNumberByQuarter(room.getId(), 1, 3, year));
                        reportData.setMoney(roomBus.getSumMoneyByQuarter(room.getId(), 1, 3, year));

                        table.add(reportData);
                    }
                    break;
                case Constant.QUARTER_2:
                    for (Room room : rooms) {
                        ReportData reportData = new ReportData();
                        reportData.setRoomName(room.getName());
//                        reportData.setRentedNum(roomBus.getRentedNumberByQuarter(room.getId(), 4, 6, year));
                        reportData.setMoney(roomBus.getSumMoneyByQuarter(room.getId(), 4, 6, year));
                        table.add(reportData);
                    }
                    break;
                case Constant.QUARTER_3:
                    for (Room room : rooms) {
                        ReportData reportData = new ReportData();
                        reportData.setRoomName(room.getName());
//                        reportData.setRentedNum(roomBus.getRentedNumberByQuarter(room.getId(), 7, 9, year));
                        reportData.setMoney(roomBus.getSumMoneyByQuarter(room.getId(), 7, 9, year));
                        table.add(reportData);
                    }

                    break;
                case Constant.QUARTER_4:
                    for (Room room : rooms) {
                        ReportData reportData = new ReportData();
                        reportData.setRoomName(room.getName());
//                        reportData.setRentedNum(roomBus.getRentedNumberByQuarter(room.getId(), 10, 12, year));
                        reportData.setMoney(roomBus.getSumMoneyByQuarter(room.getId(), 10, 12, year));
                        
                        table.add(reportData);
                    }
                    break;
            }

        }
        int size = rooms.size();
        
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
