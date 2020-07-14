
package MotelManagement.controllers;

import MotelManagement.bus.GenderBus;
import MotelManagement.bus.GuestBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.bus.StateBus;
import MotelManagement.dto.Gender;
import MotelManagement.dto.Guest;
import MotelManagement.dto.GuestInfo;
import MotelManagement.dto.Room;
import MotelManagement.dto.State;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteGuestServlet extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GuestBus guestBus = new GuestBus();
        GenderBus genderBus = new GenderBus();
        StateBus stateBus = new StateBus();
        
        RoomBus roomBus = new RoomBus();
        
        String guestId = request.getParameter("guest_id");
        
        Guest guest = guestBus.getGuest(guestId);
        Gender gender = genderBus.getGender(guest.getGenderId());
        Room room = roomBus.getRoom(guest.getRoomId());
        State state = stateBus.getState(guest.getStateId());
        
        GuestInfo guestInfo = new GuestInfo();
        guestInfo.setId(guest.getId());
        guestInfo.setName(guest.getName());
        guestInfo.setBirthday(guest.getBirthday());
        guestInfo.setGender(gender.getName());
        guestInfo.setIdentityNumber(guest.getIdentityNumber());
        guestInfo.setHomeTown(guest.getHomeTown());
        guestInfo.setOccupation(guest.getOccupation());
        guestInfo.setRoom(room.getName());
        guestInfo.setStartDate(guest.getStartDate());
        guestInfo.setState(state.getName());
        
        
        request.setAttribute("guest", guestInfo);
        String path = "WEB-INF/views/guest/delete.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String guestId = request.getParameter("guest_id");
        Guest guest = new Guest();
        
        guest.setId(guestId);
        
        GuestBus guestBus = new GuestBus();
        guestBus.delete(guest);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListGuest");
        dispatcher.forward(request, response);
    }
}
