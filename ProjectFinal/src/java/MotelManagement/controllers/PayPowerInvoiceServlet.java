package MotelManagement.controllers;

import MotelManagement.bus.InvoiceBus;
import MotelManagement.bus.PowerInfoBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.GuestPowerInvoice;
import MotelManagement.dto.Invoice;
import MotelManagement.dto.Room;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PayPowerInvoiceServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("id");
        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));
        
        RoomBus roomBus = new RoomBus();
        InvoiceBus invoiceBus = new InvoiceBus();
        
        Room room = roomBus.getRoom(roomId);
        
        //Calculate from date and to date
        Date fromDate = invoiceBus.calculateFromDate(roomId, month, year);
        Date toDate = invoiceBus.calculateToDate(fromDate);
        Date now = new Date();
        
        List<Invoice> roomDebtInvoices = invoiceBus.getRoomDebtInvoices(roomId);
        double preDebt = 0;
        for (Invoice invoice : roomDebtInvoices) {
            preDebt = preDebt + invoice.getDebt();
        }
        
        PowerInfoBus powerInfoBus = new PowerInfoBus();
        int oldElectricityIndex = powerInfoBus.getOldElectricIndex(roomId, month, year);
        int newElectricityIndex = powerInfoBus.getElectricIndex(roomId, month, year);
        
        int oldWaterIndex = powerInfoBus.getOldWaterIndex(roomId, month, year);
        int newWaterIndex = powerInfoBus.getWaterIndex(roomId, month, year);
        
        double electricMoney = powerInfoBus.calculateElectricBill(roomId, month, year);
        double waterMoney = powerInfoBus.calculateWaterBill(roomId, month, year);
        
        GuestPowerInvoice guestPowerInvoice = new GuestPowerInvoice(); 
        guestPowerInvoice.setRoomId(roomId);
        guestPowerInvoice.setRoomName(room.getName());
        
        guestPowerInvoice.setFromDate(fromDate);
        guestPowerInvoice.setToDate(toDate);
        
        guestPowerInvoice.setOldElectricityIndex(oldElectricityIndex);
        guestPowerInvoice.setNewElectricityIndex(newElectricityIndex);
        
        guestPowerInvoice.setOldWaterIndex(oldWaterIndex);
        guestPowerInvoice.setNewWaterIndex(newWaterIndex);
        
        guestPowerInvoice.setElectricMoney(electricMoney);
        guestPowerInvoice.setWaterMoney(waterMoney);
        
        guestPowerInvoice.setDebt(waterMoney + electricMoney);
        guestPowerInvoice.setPreDebt(preDebt);
        
        request.setAttribute("now", now);
        request.setAttribute("invoice", guestPowerInvoice);
        request.setAttribute("id", roomId);
        request.setAttribute("month", month);
        request.setAttribute("year", year);
            
            
        String path = "WEB-INF/views/invoice/power_invoice.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String roomId = request.getParameter("id");
        String dateStr = request.getParameter("date");
        String collectionDateStr = request.getParameter("collection-date");
        String proceedStr = request.getParameter("guest-money");
        
        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));
        
        if (proceedStr.equals("")) {
            String errProceedMsg = "Vui lòng nhập số tiền khách đưa";
            
            request.setAttribute("errProceedMsg", errProceedMsg);
            request.setAttribute("id", roomId);
            request.setAttribute("month", month);
            request.setAttribute("year", year);
            
            doGet(request, response);
            return;
        }
        
        double proceed = Double.valueOf(proceedStr);
        double debt = Double.valueOf(request.getParameter("debt"));
        double excessCash = Double.valueOf(request.getParameter("excess-cash"));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            Date collectionDate = formatter.parse(collectionDateStr);
            
            Invoice invoice = new Invoice();
            invoice.setRoomId(roomId);
            invoice.setDate(date);
            invoice.setCollectionDate(collectionDate);
            invoice.setContent(Constant.POWER_INVOICE_CONTENT + (calendar.get(Calendar.MONTH) + 1));
            invoice.setDebt(debt);
            invoice.setExcessCash(excessCash);
            invoice.setProceeds(proceed);
            
            InvoiceBus invoiceBus = new InvoiceBus();
            invoiceBus.insert(invoice);
            
            List<Invoice> debtInvoices = invoiceBus.getDebtInvoices();
            for (Invoice debtInvoice : debtInvoices) {
                debtInvoice.setDebt(0);
                
                invoiceBus.update(invoice);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Invoice");
            dispatcher.forward(request, response);
        }
        catch (ParseException e) {
            System.err.println(e.getMessage());
            
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
