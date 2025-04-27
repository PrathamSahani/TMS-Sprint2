// UpdateUserProfile.java
package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.tms.beans.TrainException;
import com.tms.beans.UserBean;
import com.tms.constant.UserRole;
import com.tms.service.UserService;
import com.tms.service.impl.UserServiceImpl;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/updateuserprofile")
public class UpdateUserProfile extends HttpServlet {
    private UserService userService = new UserServiceImpl(UserRole.CUSTOMER);

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        UserBean ub = TrainUtil.getCurrentCustomer(req);

        String fName = req.getParameter("firstname");
        String lName = req.getParameter("lastname");
        String addR = req.getParameter("address");
        long phNo = Long.parseLong(req.getParameter("phone"));
        String mailId = req.getParameter("mail");

        ub.setFName(fName);
        ub.setLName(lName);
        ub.setAddr(addR);
        ub.setPhNo(phNo);
        ub.setMailId(mailId);

        try {
            String message = userService.updateUser(ub);
            if ("SUCCESS".equalsIgnoreCase(message)) {
                res.sendRedirect(req.getContextPath() + "/edituserprofile?message=Profile+updated+successfully&messageType=success");
            } else {
                res.sendRedirect(req.getContextPath() + "/edituserprofile?message=Failed+to+update+profile.+Please+enter+valid+information&messageType=danger");
            }
        } catch (Exception e) {
            res.sendRedirect(req.getContextPath() + "/edituserprofile?message=Error+updating+profile:+"
                    + e.getMessage().replace(" ", "+") + "&messageType=danger");
        }
    }
}