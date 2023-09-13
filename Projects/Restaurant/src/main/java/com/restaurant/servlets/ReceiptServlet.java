package com.restaurant.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Receipt.html")
@ServletSecurity(@HttpConstraint(rolesAllowed= {"user"}))
public class ReceiptServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		Double total = (Double) session.getAttribute("total");
		
		if (total == null) {
			response.sendRedirect("/order.html");
			return;
		}
		
//		String total = request.getParameter("total");
		
		out.println("<html><body> <h1> Welocome to Harsh's Restaurant! </h1>");
		out.println("Your total is: $" + total);
		out.println("</body></html>");
		out.close();
	}
}