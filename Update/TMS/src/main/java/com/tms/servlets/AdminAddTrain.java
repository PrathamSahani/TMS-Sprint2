package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.constant.ResponseCode;
import com.tms.constant.UserRole;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

@WebServlet("/adminaddtrain")
public class AdminAddTrain extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TrainService trainService = new TrainServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);

        try {
            TrainBean train = new TrainBean();
            train.setTr_no(Long.parseLong(req.getParameter("trainno")));
            train.setTr_name(req.getParameter("trainname").toUpperCase());
            train.setFrom_stn(req.getParameter("fromstation").toUpperCase());
            train.setTo_stn(req.getParameter("tostation").toUpperCase());
            train.setSeats(Integer.parseInt(req.getParameter("available")));
            train.setFare(Double.parseDouble(req.getParameter("fare")));

            String message = trainService.addTrain(train);

            if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(message)) {
                req.setAttribute("alertType", "success");
                req.setAttribute("alertMessage", "Train Added Successfully!");
            } else {
                req.setAttribute("alertType", "danger");
                req.setAttribute("alertMessage", "Error in filling the train details.");
            }

        } catch (Exception e) {
            req.setAttribute("alertType", "danger");
            req.setAttribute("alertMessage", "Exception occurred: " + e.getMessage());
        }

        req.getRequestDispatcher("AddTrains.jsp").forward(req, res);
    }
}
