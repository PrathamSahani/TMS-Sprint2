package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.tms.beans.TrainBean;
import com.tms.beans.TrainException;
import com.tms.constant.UserRole;
import com.tms.service.TrainService;
import com.tms.service.impl.TrainServiceImpl;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/userviewtrainfwd")
public class UserViewTrainFwd extends HttpServlet {

    TrainService trainService = new TrainServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
        try {
            List<TrainBean> trains = trainService.getAllTrains();
            req.setAttribute("trains", trains);
            req.getRequestDispatcher("UserViewTrains.jsp").forward(req, res);
        } catch (Exception e) {
            throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
        }
    }
}
