package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.InvoiceBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.GuestRoomInvoices;
import MotelManagement.dto.Invoice;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomInvoice;
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

public class GuestRoomInvoiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");

        RequestDispatcher dispatcher;
        if (user != null) {

            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);

            if (role.equals(Constant.GUEST)) {
                RoomBus roomBus = new RoomBus();
                Room room = roomBus.getRoom(user);
                
                String roomId = room.getId();

                Calendar calendar = Calendar.getInstance();
                int presentMonth = calendar.get(Calendar.MONTH) + 1;

                //get last room invoice
                InvoiceBus invoiceBus = new InvoiceBus();

                List<RoomInvoice> roomInvoices = getRoomInvoices(roomId);
                
                GuestRoomInvoices guestRoomInvoice = new GuestRoomInvoices();
                guestRoomInvoice.setRoomName(room.getName());
                guestRoomInvoice.setInvoices(roomInvoices);

                request.setAttribute("room", guestRoomInvoice);

                String path = "WEB-INF/views/invoice/guest_room_invoice.jsp";
                dispatcher = request.getRequestDispatcher(path);
            }
            else 
                dispatcher = request.getRequestDispatcher("/Login");
            
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

    private List<RoomInvoice> getRoomInvoices(String roomId) {
        RoomBus roomBus = new RoomBus();
        Room room = roomBus.getRoom(roomId);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        Date startDate = roomBus.getStartDate(roomId);
        calendar.setTime(startDate);

        InvoiceBus invoiceBus = new InvoiceBus();

        List<RoomInvoice> roomInvoices = new ArrayList<>();

        while (startDate.before(now)) {
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            Invoice invoice = invoiceBus.getRoomInvoice(roomId, month, year);
            if (invoice == null) {
                RoomInvoice roomInvoice = new RoomInvoice();
                roomInvoice.setMonth(month);
                roomInvoice.setYear(year);
                roomInvoice.setMoney(roomBus.getPrice(roomId));

                roomInvoices.add(roomInvoice);
            }

            calendar.add(Calendar.MONTH, 1);
            startDate = calendar.getTime();
        }
        return roomInvoices;
    }
}
