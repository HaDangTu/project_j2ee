/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotelManagement.controllers;

import MotelManagement.bus.GenderBus;
import MotelManagement.bus.GuestBus;
import MotelManagement.bus.RoomBus;
import MotelManagement.dto.Gender;
import MotelManagement.dto.Guest;
import MotelManagement.dto.Room;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home
 */
public class EditGuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String guestId = request.getParameter("guest_id");

        GuestBus guestBus = new GuestBus();
        RoomBus roomBus = new RoomBus();
        GenderBus genderBus = new GenderBus();

        Guest guest = guestBus.getGuest(guestId);
        List<Room> rooms = roomBus.getAll();
        List<Gender> genders = genderBus.getAll();

        request.setAttribute("rooms", rooms);
        request.setAttribute("genders", genders);
        request.setAttribute("guest", guest);

        String path = "WEB-INF/views/guest/edit.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("guest_id");
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
            guest.setId(id);
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

            guestBus.update(guest);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListGuest");
            dispatcher.forward(request, response);
        }
    }
}
