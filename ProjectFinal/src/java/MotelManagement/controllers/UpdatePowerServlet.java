package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.PowerInfoBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Guest;
import MotelManagement.dto.PowerInfo;
import MotelManagement.dto.PowerInfoEx;
import MotelManagement.dto.Room;
import MotelManagement.util.Constant;
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
import javax.servlet.http.HttpSession;

public class UpdatePowerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");

        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();

            String role = accountBus.getRole(user);

            if (role.equals(Constant.OWNER)) {
                List<PowerInfoEx> powerInfoExs = prepareData();

                request.setAttribute("powerInfos", powerInfoExs);

                String path = "WEB-INF/views/power/update_power.jsp";
                dispatcher = request.getRequestDispatcher(path);

            } else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        } else {
            dispatcher = request.getRequestDispatcher("/Login");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        PowerInfoBus powerInfoBus = new PowerInfoBus();

        String roomId = request.getParameter("room-id");
        String toDateStr = request.getParameter("to-date");
        String newElecIndexStr = request.getParameter("new-electric-index");
        String newWaterIndexStr = request.getParameter("new-water-index");

        if (!roomId.equals("")) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date toDate = formatter.parse(toDateStr);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(toDate);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);

                String electricErrMsg = powerInfoBus.isValidElecIndex(roomId, newElecIndexStr, month, year);
                String waterErrMsg = powerInfoBus.isValidWaterIndex(roomId, newWaterIndexStr, month, year);

                if (!electricErrMsg.equals("") || !waterErrMsg.equals("")) {
                    request.setAttribute("electricErrMsg", electricErrMsg);
                    request.setAttribute("waterErrMsg", waterErrMsg);
                } else {

                    long electricityIndex = Long.valueOf(newElecIndexStr);
                    long waterIndex = Long.valueOf(newWaterIndexStr);

                    PowerInfo powerInfo = new PowerInfo();
                    powerInfo.setRoomId(roomId);
                    powerInfo.setDate(toDate);
                    powerInfo.setElectricityIndex(electricityIndex);
                    powerInfo.setWaterIndex(waterIndex);

                    powerInfoBus.insert(powerInfo);

                    List<PowerInfoEx> powerInfoExs = prepareData();

                    request.setAttribute("powerInfos", powerInfoExs);

                    String path = "WEB-INF/views/power/update_power.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);
                }

            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        } else {
            request.setAttribute("roomErrMsg", "Vui lòng chọn phòng ở bảng bên dưới");
        }

    }

    private List<PowerInfoEx> prepareData() {
        PowerInfoBus powerInfoBus = new PowerInfoBus();
        RoomBus roomBus = new RoomBus();

        Calendar calendar = Calendar.getInstance();
        //DO tháng trong java bắt đầu từ 0 nên ko cần trừ 1 để về tháng trước
        int prevMonth = calendar.get(Calendar.MONTH);

        if (prevMonth == 0) {
            prevMonth = 12;
        }

        //Lấy danh sách các phòng đang cho thuê
        List<Room> rooms = roomBus.getRentedRooms();

        List<PowerInfoEx> powerInfoExs = new ArrayList<>();
        for (Room room : rooms) {

            String roomId = room.getId();
            //Lấy phòng chưa cập nhật chỉ số điện nước
            PowerInfo powerInfo = powerInfoBus.getPowerInfo(roomId, prevMonth + 1);

            if (powerInfo == null) { //chưa cập nhật
                //Lây chỉ số điện nước tháng trước của phòng
                powerInfo = powerInfoBus.getPowerInfo(roomId, prevMonth);

                PowerInfoEx powerInfoEx = new PowerInfoEx();
                powerInfoEx.setRoomId(roomId);
                powerInfoEx.setRoomName(room.getName());
                
                if (powerInfo == null) { //Tháng đầu tiên cho thuê phòng 
                    //Lấy ngày bắt đầu ở của khách trọ

                    List<Guest> guests = roomBus.getGuests(roomId);
                    Guest guest = guests.get(0);

                    powerInfoEx.setDate(guest.getStartDate());
                    powerInfoEx.setElectricityIndex(0);
                    powerInfoEx.setWaterIndex(0);
                } else {
                    
                    powerInfoEx.setId(powerInfo.getId());
                    powerInfoEx.setDate(powerInfo.getDate());
                    powerInfoEx.setElectricityIndex(powerInfo.getElectricityIndex());
                    powerInfoEx.setWaterIndex(powerInfo.getWaterIndex());
                }

                powerInfoExs.add(powerInfoEx);
            }

        }

        return powerInfoExs;
    }
}
