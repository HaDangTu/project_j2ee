package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomType;
import MotelManagement.util.Constant;
import MotelManagement.util.Generator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String roomId = request.getParameter("room_id");
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);

            if (role.equals(Constant.OWNER)) {
                RoomBus roomBus = new RoomBus();
                Room room = roomBus.getRoom(roomId);

                RoomTypeBus roomTypeBus = new RoomTypeBus();
                List<RoomType> roomTypes = roomTypeBus.getAll();

                request.setAttribute("roomTypes", roomTypes);
                request.setAttribute("room", room);

                String path = "WEB-INF/views/room/edit.jsp";

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
        String roomId = request.getParameter("room_id");
        String roomName = request.getParameter("name");
        String roomType = request.getParameter("room-type");
        boolean hasError = false;

        RoomBus roomBus = new RoomBus();
        String nameErrMsg = roomBus.isValidRoomName(roomName);
        RequestDispatcher dispatcher;
        if (!nameErrMsg.equals("")) {
            request.setAttribute("nameErrMsg", nameErrMsg);
            hasError = true;
        }

        if (hasError) {
            RoomTypeBus roomTypeBus = new RoomTypeBus();

            List<RoomType> roomTypes = roomTypeBus.getAll();
            request.setAttribute("roomTypes", roomTypes);
            String path = "WEB-INF/views/room/edit.jsp";
            dispatcher = request.getRequestDispatcher(path);
        } else {

            Room room = new Room();
            room.setId(roomId);
            room.setName(roomName);
            room.setRoomTypeId(roomType);

            roomBus.update(room);

            dispatcher = request.getRequestDispatcher("/ListRoom");

        }
        dispatcher.forward(request, response);
    }
}
