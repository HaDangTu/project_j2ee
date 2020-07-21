package MotelManagement.controllers;

import MotelManagement.bus.InvoiceBus;
import MotelManagement.bus.PowerInfoBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.GuestInvoice;
import MotelManagement.dto.Invoice;
import MotelManagement.dto.PowerInvoice;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomInvoice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GettingInvoicesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String roomId = request.getParameter("room-id");

        List<RoomInvoice> roomInvoices = getRoomInvoices(roomId);
        List<PowerInvoice> powerInvoices = getPowerInvoices(roomId);

        RoomBus roomBus = new RoomBus();
        Room room = roomBus.getRoom(roomId);

        List<Room> rooms = roomBus.getRentedRooms();

        GuestInvoice guestInvoice = new GuestInvoice();
        guestInvoice.setId(roomId);
        guestInvoice.setRoomName(room.getName());
        guestInvoice.setRoomInvoices(roomInvoices);
        guestInvoice.setPowerInvoices(powerInvoices);

        request.setAttribute("rooms", rooms);
        request.setAttribute("guest", guestInvoice);

        String path = "WEB-INF/views/invoice/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
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
