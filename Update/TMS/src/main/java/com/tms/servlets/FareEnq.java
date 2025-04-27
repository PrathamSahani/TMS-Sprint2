// src/main/java/com/tms/servlets/FareEnq.java
package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.constant.UserRole;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/fareenq")
public class FareEnq extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TrainService trainService = new TrainServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Simply forward to the JSP to show the empty form
        req.getRequestDispatcher("Fare.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // enforce customer‐only access
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        try {
            String fromStation = req.getParameter("fromstation");
            String toStation   = req.getParameter("tostation");

            List<TrainBean> trains = trainService
                    .getTrainsBetweenStations(fromStation, toStation);

            // pass everything off to the JSP
            req.setAttribute("fromStation", fromStation);
            req.setAttribute("toStation",   toStation);
            req.setAttribute("trains",       trains);

            req.getRequestDispatcher("Fare.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
