package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomGuest;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            //Get user role
            AccountBus accountBus = new AccountBus();
            RoomBus roomBus = new RoomBus();
            
            String role = accountBus.getRole(user);
            if (role.equals(Constant.OWNER)) {
                //Get all rooms
                List<Room> rooms = roomBus.getAll();
                
                //Lớp RoomGuest định nghĩa các thông tin về khách trọ của phòng
                //Xem lớp này trong package dto để biết thêm
                List<RoomGuest> roomGuests = new ArrayList<>();
                
                for (Room room : rooms) {
                    String roomId = room.getId();
                    RoomGuest roomGuest = new RoomGuest();
                    roomGuest.setRoom(room);
                    roomGuest.setMaxGuest(roomBus.getMaxNumGuest(roomId));
                    roomGuest.setGuests(roomBus.getGuests(roomId));
                    
                    roomGuests.add(roomGuest);
                }
                
                request.setAttribute("rooms", roomGuests);
            } else {
                //Get room by user
                Room room = roomBus.getRoom(user);
                String roomId = room.getId();
                RoomGuest roomGuest = new RoomGuest(room, roomBus.getGuests(roomId));
                
                request.setAttribute("room", roomGuest);
            }
            request.setAttribute("role", role);
            String path = "WEB-INF/views/home/index.jsp";
            dispatcher = request.getRequestDispatcher(path); //forward đến home page
        }
        else {
            dispatcher = request.getRequestDispatcher("/Login");
        }
        
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    
}
