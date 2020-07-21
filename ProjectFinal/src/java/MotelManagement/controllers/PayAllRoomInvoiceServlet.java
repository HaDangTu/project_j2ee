package MotelManagement.controllers;

import MotelManagement.bus.InvoiceBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.GuestInvoice;
import MotelManagement.dto.GuestRoomInvoice;
import MotelManagement.dto.Invoice;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomInvoice;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PayAllRoomInvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("id");
        InvoiceBus invoiceBus = new InvoiceBus();
        RoomBus roomBus = new RoomBus();
        Room room = roomBus.getRoom(roomId);
        
        List<RoomInvoice> roomInvoices = getRoomInvoices(roomId);
        //Tính from date và to date hóa đơn
        RoomInvoice firstInvoice = roomInvoices.get(0);
        int month = firstInvoice.getMonth();
        int year = firstInvoice.getYear();
        
        Date fromDate = invoiceBus.calculateFromDate(roomId, month, year); 
        
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(fromDate);
        calendar.add(Calendar.MONTH, roomInvoices.size());
        
        Date toDate = calendar.getTime();
        
        double debt = 0;
        for (RoomInvoice roomInvoice : roomInvoices) {
            debt += roomInvoice.getMoney();
        }
        
        List<Invoice> roomDebtInvoices = invoiceBus.getRoomDebtInvoices(roomId);
        double preDebt = 0;
        for (Invoice invoice : roomDebtInvoices) {
            preDebt = preDebt + invoice.getDebt();
        }
        
        GuestRoomInvoice invoice = new GuestRoomInvoice();
        invoice.setRoomId(roomId);
        invoice.setRoomName(room.getName());
        invoice.setFromDate(fromDate);
        invoice.setToDate(toDate);
        invoice.setDebt(debt);
        invoice.setPreDebt(preDebt);
        
        Date now = new Date();
        request.setAttribute("now", now);
        request.setAttribute("invoice", invoice);
        
        String path = "WEB-INF/views/invoice/all_room_invoice.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String roomId = request.getParameter("room-id");
        String dateStr = request.getParameter("date");
        String collectionDateStr = request.getParameter("collection-date");
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            Date collectionDate = formatter.parse(collectionDateStr);
        }catch (ParseException e) {
            System.err.println(e.getMessage());
            
        }
    }
    
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
