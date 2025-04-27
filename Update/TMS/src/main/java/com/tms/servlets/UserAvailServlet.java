package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.constant.UserRole;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/useravail")
public class UserAvailServlet extends HttpServlet {
    private TrainService trainService = new TrainServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Ensure customer is logged in
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        String trainNo = req.getParameter("trainno");
        try {
            TrainBean train = trainService.getTrainById(trainNo);

            if (train != null) {
                req.setAttribute("train", train);
            } else {
                req.setAttribute("errorMsg", "Train No. " + trainNo + " is not available!");
            }
            // forward to JSP for rendering
            req.getRequestDispatcher("Availability.jsp").forward(req, res);

        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
