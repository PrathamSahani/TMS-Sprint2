package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

import com.tms.beans.HistoryBean;
import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.constant.ResponseCode;
import com.tms.constant.UserRole;
import com.tms.service.BookingService;
import com.tms.service.TrainService;
import com.tms.service.impl.BookingServiceImpl;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/booktrains")
public class BookTrains extends HttpServlet {

    private TrainService trainService = new TrainServiceImpl();
    private BookingService bookingService = new BookingServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = res.getWriter();

        // Validate user role
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        // Include user home header
       

        // Begin Bootstrap container for results
        pw.println("<div class='container my-5'>");

        ServletContext sct = req.getServletContext();
        try {
            int seatsRequested = (int) sct.getAttribute("seats");
            String trainNo = (String) sct.getAttribute("trainnumber");
            String journeyDate = (String) sct.getAttribute("journeydate");
            String seatClass = (String) sct.getAttribute("class");
            String userMailId = TrainUtil.getCurrentUserEmail(req);

            // Format journey date
            SimpleDateFormat inputFmt = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFmt = new SimpleDateFormat("dd-MMM-yyyy");
            java.util.Date utilDate = inputFmt.parse(journeyDate);
            String formattedDate = outputFmt.format(utilDate);

            TrainBean train = trainService.getTrainById(trainNo);
            if (train == null) {
                printAlert(pw, "Invalid Train Number!", "danger");
            } else {
                int availableSeats = train.getSeats();
                if (seatsRequested > availableSeats) {
                    printAlert(pw, "Only " + availableSeats + " seats are available on this train.", "warning");
                } else {
                    // Update seat count
                    train.setSeats(availableSeats - seatsRequested);
                    String responseCode = trainService.updateTrain(train);

                    if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(responseCode)) {
                        // Create booking history
                        HistoryBean booking = new HistoryBean();
                        double totalAmount = train.getFare() * seatsRequested;
                        booking.setAmount(totalAmount);
                        booking.setFrom_stn(train.getFrom_stn());
                        booking.setTo_stn(train.getTo_stn());
                        booking.setTr_no(trainNo);
                        booking.setSeats(seatsRequested);
                        booking.setMailId(userMailId);
                        booking.setDate(formattedDate);

                        HistoryBean transaction = bookingService.createHistory(booking);
                        
                        // Forward to GenerateTicket.jsp with required attributes
                        req.setAttribute("txn", transaction);
                        req.setAttribute("trainName", train.getTr_name());
                        req.setAttribute("seatClass", seatClass);
                        RequestDispatcher ticketDispatcher = req.getRequestDispatcher("GenerateTicket.jsp");
                        ticketDispatcher.include(req, res);
                    } else {
                        printAlert(pw, "Transaction declined. Please try again.", "danger");
                    }
                }
            }
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        } finally {
            // Clean up context attributes
            sct.removeAttribute("seats");
            sct.removeAttribute("trainnumber");
            sct.removeAttribute("journeydate");
            sct.removeAttribute("class");
        }

        pw.println("</div>"); // Close container
    }

    /** Utility to print Bootstrap alert messages */
    private void printAlert(PrintWriter pw, String message, String type) {
        pw.println("<div class='alert alert-" + type + " alert-dismissible fade show' role='alert'>");
        pw.println("  " + message + "");
        pw.println("  <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
        pw.println("</div>");
    }
}