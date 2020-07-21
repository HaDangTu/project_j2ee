package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.RoomType;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRoomTypeServlet extends HttpServlet {

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
                String path = "WEB-INF/views/room_type/delete.jsp";
                String id = request.getParameter("id");

                RoomTypeBus roomTypeBus = new RoomTypeBus();
                RoomType roomType = roomTypeBus.getRoomType(id);

                request.setAttribute("roomType", roomType);
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
        String id = request.getParameter("id");
        RoomType roomType = new RoomType();
        roomType.setId(id);

        RoomTypeBus roomTypeBus = new RoomTypeBus();
        roomTypeBus.delete(roomType);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListRoomType");
        dispatcher.forward(request, response);
    }

}
