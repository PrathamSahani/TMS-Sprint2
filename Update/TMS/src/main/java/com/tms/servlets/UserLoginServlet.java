package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tms.constant.ResponseCode;
import com.tms.constant.UserRole;
import com.tms.utils.TrainUtil;

@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Show login page on GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
    }

    // Process login on POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String uName = req.getParameter("uname");
        String pWord = req.getParameter("pword");
        String responseMsg = TrainUtil.login(req, resp, UserRole.CUSTOMER, uName, pWord);

        if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(responseMsg)) {
            // store the user in session and redirect to home
            req.getSession().setAttribute("userName", uName);
            resp.sendRedirect("userhome");
        } else {
            // back to login with error
            req.setAttribute("errorMessage", responseMsg);
            req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
        }
    }
}
