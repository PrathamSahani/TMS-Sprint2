package com.tms.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;

import com.tms.beans.TrainException;
import com.tms.beans.UserBean;
import com.tms.constant.UserRole;
import com.tms.service.UserService;
import com.tms.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/userreg")
public class UserRegServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl(UserRole.CUSTOMER);

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			UserBean user = new UserBean();
			user.setMailId(req.getParameter("mailid"));
			user.setPWord(req.getParameter("pword"));
			user.setFName(req.getParameter("firstname"));
			user.setLName(req.getParameter("lastname"));
			user.setAddr(req.getParameter("address"));

			String phoneStr = req.getParameter("phoneno");
			if (phoneStr == null || phoneStr.trim().isEmpty()) {
				throw new TrainException(400, "INVALID_INPUT", "Phone number is required.");
			}
			try {
				user.setPhNo(Long.parseLong(phoneStr.trim()));
			} catch (NumberFormatException nfe) {
				throw new TrainException(400, "INVALID_INPUT", "Phone number must be a valid number.");
			}

			String message = userService.registerUser(user);
			if ("SUCCESS".equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully !</p1></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>" + message + "</p1></div>");
			}

		} catch (TrainException te) {
			RequestDispatcher rd = req.getRequestDispatcher("UserRegister.jsp");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Error: " + te.getMessage() + "</p1></div>");
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}
}
