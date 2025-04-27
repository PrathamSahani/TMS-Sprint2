package com.tms.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.tms.beans.TrainException;
import com.tms.constant.ResponseCode;
import com.tms.constant.UserRole;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

import java.io.IOException;

@WebServlet("/admincancletrain")
public class AdminCancleTrain extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final TrainService trainService = new TrainServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        // enforce admin authorization
        TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);

        try {
            String trainNo = req.getParameter("trainno");
            String result = trainService.deleteTrainById(trainNo);

            String message;
            if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(result)) {
                message = "Train number " + trainNo + " has been deleted successfully.";
            } else {
                message = "Train No. " + trainNo + " is not available!";
            }

            // pass message to JSP and forward
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
