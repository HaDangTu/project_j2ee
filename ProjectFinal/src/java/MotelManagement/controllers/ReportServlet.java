package MotelManagement.controllers;

import MotelManagement.bus.AccountBus;
import MotelManagement.bus.InvoiceBus;
import MotelManagement.dto.ApplicationUser;
import MotelManagement.dto.ReportData;
import MotelManagement.util.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ReportServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        
        RequestDispatcher dispatcher;
        if (user != null) {
            AccountBus accountBus = new AccountBus();
            String role = accountBus.getRole(user);
            
            if (role.equals(Constant.OWNER)) {
                Calendar calendar = Calendar.getInstance();
                int presentYear = calendar.get(Calendar.YEAR);
                int startYear = Constant.START_YEAR;
                
                List<Integer> years = new ArrayList();
                while (startYear <= presentYear) {
                    years.add(startYear);
                    startYear++;
                }
                
                request.setAttribute("years", years);
                
                List<ReportData> table = (List<ReportData>) request.getAttribute("table");
                
                if (table != null) {
                    request.setAttribute("table", table);
                    
                }
                
                String path = "WEB-INF/views/report/report_sales.jsp";
                dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            }
            else {
                dispatcher = request.getRequestDispatcher("/Login");
            }
        }
        else {
            dispatcher = request.getRequestDispatcher("/Login");
        }
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
