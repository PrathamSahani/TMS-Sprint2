// ViewUserProfile.java
package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;

import com.tms.beans.UserBean;
import com.tms.constant.UserRole;
import com.tms.utils.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/viewuserprofile")
public class ViewUserProfile extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

        // include header/nav
        RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
        rd.include(req, res);

        String cp = req.getContextPath();

        pw.println("<div class='container d-flex flex-column justify-content-center align-items-center' style='min-height:70vh;'>");

        // optional alert
        String msg = req.getParameter("msg"), type = req.getParameter("msgType");
        if (msg != null) {
            pw.println("<div class='alert alert-" + (type!=null?type:"info")
                     + " text-center w-100' role='alert'>" + msg + "</div>");
        }



        // profile card
        UserBean ub = TrainUtil.getCurrentCustomer(req);
        pw.println("  <div class='row justify-content-center w-100'>");
        pw.println("    <div class='col-md-8'>");
        pw.println("      <div class='card shadow-sm'>");
        pw.println("        <div class='card-header text-center'><h4>User Profile</h4></div>");
        pw.println("        <div class='card-body'>");
        pw.println("          <table class='table table-bordered'>");
        pw.println("            <tr><th>Profile Photo</th><td>Not Available</td></tr>");
        pw.println("            <tr><th>User Name</th><td>" + ub.getMailId() + "</td></tr>");
        pw.println("            <tr><th>Password</th><td><input type='password' class='form-control' disabled value='"
                 + ub.getPWord() + "'/></td></tr>");
        pw.println("            <tr><th>First Name</th><td>" + ub.getFName() + "</td></tr>");
        pw.println("            <tr><th>Last Name</th><td>" + ub.getLName() + "</td></tr>");
        pw.println("            <tr><th>Address</th><td>" + ub.getAddr() + "</td></tr>");
        pw.println("            <tr><th>Phone No</th><td>" + ub.getPhNo() + "</td></tr>");
        pw.println("            <tr><th>Mail Id</th><td>" + ub.getMailId() + "</td></tr>");
        pw.println("          </table>");
        pw.println("        </div>");
        pw.println("      </div>");
        pw.println("    </div>");
        pw.println("  </div>");

        pw.println("</div>");  // flex container

        pw.println("<footer class='footer bg-light py-3'>");
        pw.println("  <div class='container text-center'><span class='text-muted'>&copy; 2025 NITRTC. All rights reserved.</span></div>");
        pw.println("</footer>");
    }
}
