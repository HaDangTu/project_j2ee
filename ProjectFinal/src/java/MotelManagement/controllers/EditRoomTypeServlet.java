
package MotelManagement.controllers;

import MotelManagement.bus.RoomTypeBus;
import MotelManagement.dto.RoomType;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditRoomTypeServlet extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //        HttpSession session = request.getSession();
//        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
//        
        RequestDispatcher dispatcher;
//        if (user != null) {
//            AccountBus accountBus = new AccountBus();
//            String role = accountBus.getRole(user);
//            
//            if (role.equals(Constant.OWNER)) {
                   String path = "WEB-INF/views/room_type/edit.jsp";
                   String id = request.getParameter("id");
                   
                   RoomTypeBus roomTypeBus = new RoomTypeBus();
                   RoomType roomType = roomTypeBus.getRoomType(id);
                   
                   request.setAttribute("roomType", roomType);
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
        
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("room-type-name");
        String numOfGuestStr = request.getParameter("num-of-guest");
        String priceStr = request.getParameter("price");
        
        RequestDispatcher dispatcher;
        boolean hasError = false;
        
        RoomTypeBus roomTypeBus = new RoomTypeBus();
        
        //TODO logic code to check data is valid
        if (hasError) {
            String path = "WEB-INF/views/room_type/edit.jsp";
            dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
        else {
            RoomType roomType = new RoomType();
            NumberFormat formatter = NumberFormat.getInstance();
            
            roomType.setId(id);
            roomType.setName(name);
            try {
                roomType.setNumOfGuest(formatter.parse(numOfGuestStr).intValue());
                roomType.setPrice(formatter.parse(priceStr).doubleValue());
            } catch (ParseException ex) {
                Logger.getLogger(CreateNewRoomTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            roomTypeBus.update(roomType);
            
            dispatcher = request.getRequestDispatcher("/ListRoomType");
            dispatcher.forward(request, response);
        }
    }

   
}
