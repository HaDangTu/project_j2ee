package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.dto.ApplicationUser;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MotelManagement.bus.GenderBus;
import MotelManagement.bus.GuestBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.bus.StateBus;
import MotelManagement.dto.Gender;
import MotelManagement.dto.Guest;
import MotelManagement.dto.Room;
import MotelManagement.dto.State;
import MotelManagement.util.Constant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class CreateNewGuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

//        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        AccountBus accountBus = new AccountBus();

        RequestDispatcher dispatcher;
//        if (user != null) {

//            String role = accountBus.getRole(user);
//            
//            if (role.equals(Constant.OWNER)) {
        RoomBus roomBus = new RoomBus();
        GenderBus genderBus = new GenderBus();
        StateBus stateBus = new StateBus();

        List<Room> rooms = roomBus.getNotFullRooms();
        List<Gender> genders = genderBus.getAll();
        List<State> states = stateBus.getAll();

        request.setAttribute("rooms", rooms);
        request.setAttribute("genders", genders);
        request.setAttribute("states", states);

        String path = "WEB-INF/views/guest/create.jsp";
        dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
//            }
//            else {
//                dispatcher = request.getRequestDispatcher("/Login");
//            }
//        }
//        else 
//            dispatcher = request.getRequestDispatcher("/Login");
//        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String genderId = request.getParameter("gender");
        String identityNumber = request.getParameter("identity_number");
        String homeTown = request.getParameter("home_town");
        String occupation = request.getParameter("occupation");
        String roomId = request.getParameter("room");
        String startDate = request.getParameter("start_date");

        GuestBus guestBus = new GuestBus();
        //logic code to validate data
        String nameErrMsg = guestBus.isValidName(name);
        String birthdayErrMsg = guestBus.isValidDate(birthday);
        String identityErrMsg = guestBus.isValidIdentityNumber(identityNumber);
        String homeTownErrMsg = guestBus.isValidName(homeTown);
        String occupationErrMsg = guestBus.isValidName(occupation);
        String startDateErrMsg = guestBus.isValidDate(startDate);

        boolean flag = false;
        if (!nameErrMsg.equals("")) {
            request.setAttribute("nameErrMsg", nameErrMsg);
            flag = true;
        }

        if (!birthdayErrMsg.equals("")) {
            request.setAttribute("birthdayErrMsg", birthdayErrMsg);
            flag = true;
        }

        if (!identityErrMsg.equals("")) {
            request.setAttribute("identityErrMsg", identityErrMsg);
            flag = true;
        }

        if (!homeTownErrMsg.equals("")) {
            request.setAttribute("homeTownErrMsg", homeTownErrMsg);
            flag = true;
        }

        if (!occupationErrMsg.equals("")) {
            request.setAttribute("occupationErrMsg", occupationErrMsg);
            flag = true;
        }

        if (!startDateErrMsg.equals("")) {
            request.setAttribute("startDateErrMsg", startDateErrMsg);
            flag = true;
        }

        if (flag) {
            String path = "WEB-INF/views/guest/create.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } else {
            Guest guest = new Guest();
            guest.setName(name);
            guest.setGenderId(genderId);
            guest.setIdentityNumber(identityNumber);
            guest.setHomeTown(homeTown);
            guest.setOccupation(occupation);
            guest.setStateId("S01");
            guest.setRoomId(roomId);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(birthday);
                guest.setBirthday(date);

                date = formatter.parse(startDate);
                guest.setStartDate(date);
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }

            guestBus.insert(guest);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListGuest");
            dispatcher.forward(request, response);
        }
    }
}
