package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;

import com.tms.constant.UserRole;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/booktrainbyref")
public class BookTrainByRef extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
        String emailId = TrainUtil.getCurrentUserEmail(req);
        String trainParam = req.getParameter("trainNo");
        long trainNo = Long.parseLong(trainParam == null ? "0" : trainParam.trim());

        int seat = 1;
        String fromStn = req.getParameter("fromStn");
        String toStn = req.getParameter("toStn");

        RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.jsp");
        rd.include(req, res);

        pw.println("<div class='container mt-5'>");
        pw.println("<div class='card shadow-lg'>");
        pw.println("<div class='card-header bg-primary text-white'><h5>Your Ticket Booking Information</h5></div>");
        pw.println("<div class='card-body'>");
        pw.println("<form action='payment' method='post'>");
        pw.println("<div class='row mb-3'><div class='col-md-6'><label class='form-label'>User ID</label><input type='text' class='form-control' value='" + emailId + "' readonly></div>");
        pw.println("<div class='col-md-6'><label class='form-label'>Train No</label><input type='text' class='form-control' value='" + trainNo + "' readonly></div></div>");

        pw.println("<div class='row mb-3'><div class='col-md-6'><label class='form-label'>From Station</label><input type='text' class='form-control' value='" + fromStn + "' readonly></div>");
        pw.println("<div class='col-md-6'><label class='form-label'>To Station</label><input type='text' class='form-control' value='" + toStn + "' readonly></div></div>");

        pw.println("<input type='hidden' name='trainnumber' value='" + trainNo + "'>");

        pw.println("<div class='row mb-3'><div class='col-md-6'><label class='form-label'>Journey Date</label><input type='date' name='journeydate' class='form-control' value='" + LocalDate.now() + "'></div>");
        pw.println("<div class='col-md-6'><label class='form-label'>No of Seats</label><input type='number' name='seats' class='form-control' value='" + seat + "'></div></div>");

        pw.println("<div class='row mb-3'><div class='col-md-6'><label class='form-label'>Class</label><select name='class' class='form-select' required>"
                + "<option value='Sleeper(SL)'>Sleeper(SL)</option>"
                + "<option value='Second Sitting(2S)'>Second Sitting(2S)</option>"
                + "<option value='AC First Class(1A)'>AC First Class(1A)</option>"
                + "<option value='AC 2 Tier(2A)'>AC 2 Tier(2A)</option>"
                + "</select></div>");
        pw.println("<div class='col-md-6'><label class='form-label'>Berth Preference</label><select name='berth' class='form-select'>"
                + "<option value='NO'>No Preference</option>"
                + "<option value='LB'>Lower Berth(LB)</option>"
                + "<option value='UB'>Upper Berth(UB)</option>"
                + "<option value='C'>Cabin</option>"
                + "</select></div></div>");

        pw.println("<div class='text-center'><button type='submit' class='btn btn-success'>Pay And Book</button></div>");
        pw.println("</form></div></div></div>");
    }
}
