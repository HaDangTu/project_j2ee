package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.InvoiceBus;
import MotelManagement.bus.PowerInfoBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.GuestPowerInvoices;
import MotelManagement.dto.Invoice;
import MotelManagement.dto.PowerInvoice;
import MotelManagement.dto.Room;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GuestPowerInvoiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //TODO logic code to get room id from login user
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {

            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);
            if (role.equals(Constant.GUEST)) {
                RoomBus roomBus = new RoomBus();
                InvoiceBus invoiceBus = new InvoiceBus();
                PowerInfoBus powerInfoBus = new PowerInfoBus();

                Room room = roomBus.getRoom(user);
                String roomId = room.getId();

                List<PowerInvoice> invoices = getPowerInvoices(roomId);

                GuestPowerInvoices guestPowerInvoice = new GuestPowerInvoices();
                guestPowerInvoice.setRoomName(room.getName());
                guestPowerInvoice.setInvoices(invoices);

                request.setAttribute("room", guestPowerInvoice);
                String path = "WEB-INF/views/invoice/guest_power_invoice.jsp";
                dispatcher = request.getRequestDispatcher(path);
                
            }
            else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        }
        else 
            dispatcher = request.getRequestDispatcher("/Login");
        
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
    
    private List<PowerInvoice> getPowerInvoices(String roomId) {
        RoomBus roomBus = new RoomBus();
        InvoiceBus invoiceBus = new InvoiceBus();
        PowerInfoBus powerInfoBus = new PowerInfoBus();

        Room room = roomBus.getRoom(roomId);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        Date startDate = roomBus.getStartDate(roomId);
        calendar.setTime(startDate);

        List<PowerInvoice> invoices = new ArrayList<>();

        while (startDate.before(now)) {

            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            Invoice invoice = invoiceBus.getPowerInvoice(roomId, month, year);

            if (invoice == null) {

                int newElectricityIndex = powerInfoBus.getElectricIndex(roomId, month, year);
                int newWaterIndex = powerInfoBus.getWaterIndex(roomId, month, year);
                
                int oldElectricityIndex = powerInfoBus.getOldElectricIndex(roomId, month, year);
                int oldWaterIndex = powerInfoBus.getOldWaterIndex(roomId, month, year);

                if (newElectricityIndex == 0 || newWaterIndex == 0) {
                    calendar.add(Calendar.MONTH, 1);
                    startDate = calendar.getTime();
                    continue;
                }

                double electricMoney = powerInfoBus.calculateElectricBill(roomId, month, year);
                double waterMoney = powerInfoBus.calculateWaterBill(roomId, month, year);

                PowerInvoice powerInvoice = new PowerInvoice();
                powerInvoice.setMonth(month);
                powerInvoice.setYear(year);
                powerInvoice.setOldElectricityIndex(oldElectricityIndex);
                powerInvoice.setNewElectricityIndex(newElectricityIndex);
                powerInvoice.setOldWaterIndex(oldWaterIndex);
                powerInvoice.setNewWaterIndex(newWaterIndex);
                powerInvoice.setElectricityMoney(electricMoney);
                powerInvoice.setWaterMoney(waterMoney);

                invoices.add(powerInvoice);
            }

            calendar.add(Calendar.MONTH, 1);
            startDate = calendar.getTime();
        }
        return invoices;
    }

}
