package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tms.constant.UserRole;
import com.tms.utils.TrainUtil;

@WebServlet("/userhome")
public class UserHome extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display user home
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ensure logged in
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        // get name from session
        String userName = (String) req.getSession().getAttribute("userName");
        req.setAttribute("userName", userName);

        req.getRequestDispatcher("UserHome.jsp").forward(req, resp);
    }

    // Redirect POST to GET
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
