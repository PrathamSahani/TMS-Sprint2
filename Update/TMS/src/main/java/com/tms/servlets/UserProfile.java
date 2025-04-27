// UserProfile.java
package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;

import com.tms.constant.UserRole;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/userprofile")
public class UserProfile extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
        
        // Set attributes for JSP
        req.setAttribute("msg", req.getParameter("msg"));
        req.setAttribute("msgType", req.getParameter("msgType"));
        req.setAttribute("userName", TrainUtil.getCurrentUserName(req));
        
        RequestDispatcher rd = req.getRequestDispatcher("UserProfile.jsp");
        rd.forward(req, res);
    }
}