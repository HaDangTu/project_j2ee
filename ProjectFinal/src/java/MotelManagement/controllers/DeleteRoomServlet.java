
package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.Room;
import MotelManagement.dto.RoomInfo;
import MotelManagement.dto.RoomType;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRoomServlet extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String roomId = request.getParameter("room_id");
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        RequestDispatcher dispatcher;
        //        if (user != null) {
//            AccountBus accountBus = new AccountBus();
//            String role = accountBus.getRole(user);
//            
//            if (role.equals(Constant.OWNER)) {
                RoomBus roomBus = new RoomBus();
                Room room =  roomBus.getRoom(roomId);
                
                RoomTypeBus roomTypeBus = new RoomTypeBus();
                RoomType roomType = roomTypeBus.getRoomType(room.getRoomTypeId());
                
                RoomInfo roomInfo = new RoomInfo();
                
                roomInfo.setId(room.getId());
                roomInfo.setName(room.getName());
                roomInfo.setRoomType(roomType.getName());
                roomInfo.setUserId(room.getUserId());
                
                request.setAttribute("room", roomInfo);
                
                String path = "WEB-INF/views/room/delete.jsp";
                
                dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
//            }
//            else {
//                dispatcher = request.getRequestDispatcher("/Login");
//            }
//        }
//        else {
//            dispatcher = request.getRequestDispatcher("/Login");
//        }
//        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomId = request.getParameter("room_id");
        String userId = request.getParameter("user_id");
        
        Room room = new Room();
        room.setId(roomId);
        
        AccountBus accountBus = new AccountBus();
        ApplicationUser user = new ApplicationUser();
        user.setId(userId);
        accountBus.delete(user);
        
        RoomBus roomBus = new RoomBus();
        roomBus.delete(room);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListRoom");
        dispatcher.forward(request, response);
    }

    
}
