package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;

@WebServlet("/adminupdatetrain")
public class AdminTrainUpdate extends HttpServlet {

    private TrainService trainService = new TrainServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            String trainNo = req.getParameter("trainnumber");
            TrainBean train = trainService.getTrainById(trainNo);

            if (train != null) {
                // pass the bean to JSP for rendering the update form
                req.setAttribute("train", train);
            } else {
                // no train found → show warning on JSP
                req.setAttribute("message", "Train with number " + trainNo + " not available");
            }
            // forward always to the same JSP which now handles both search & update UI
            req.getRequestDispatcher("AdminUpdateTrain.jsp").forward(req, res);

        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }
}
