//package com.virtualpairprogrammers.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Enumeration;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//import com.virtualpairprogrammers.data.MenuDao;
//import com.virtualpairprogrammers.domain.MenuItem;
//
//public class OrderReceivedServlet extends HttpServlet {
//	
//	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
////		super.service(request, response);
//		
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
//						
//		out.println("<html><body> <h1> Welocome to Harsh's Restaurant! </h1>");
//		out.println("<h3> Your Ordes is: </h3>");
//		out.println("<ul>");
//		doPost(request, response);
//		out.println();
//		out.println("</ul>");
//		out.println("</body></html>");
//	
//		out.println("<html><body> <h1> Welocome to Harsh's Restaurant! </h1>");
//		out.println("<p>We're sorry! Your order does not contain any items ");
//		out.println("</body></html>");
//		
//		System.out.println("You need to override this method");
//		out.close();
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		
//		PrintWriter out = response.getWriter();
//
//		Enumeration<String> parameterNames = request.getParameterNames();
//
//		MenuDao dataService = new MenuDao();
//		
//		while(parameterNames.hasMoreElements()) {
//			try {
//				String paramName = parameterNames.nextElement();
//				int paramValue = Integer.parseInt(request.getParameter(paramName));
//				
//				if (paramValue > 0) {
//					MenuItem item = dataService.getItem(Integer.parseInt(paramName));
//					dataService.addToOrder(item, paramValue);
//					
//					out.println("<li>");
//					out.print(item.toString());
//					out.print(" Quantity: " + paramValue);
//					out.print("</li>");
//				}
//			} catch (NumberFormatException e) {
//				//do nothing
//			}
//		}
//		Double total = dataService.getOrderTotal();
//				
//		out.println("<h3> Thank you for your order! </h3>");
//		//print on the console
//		System.out.println("A new order has been placed.");
//		
////		out.println("Your total is: $" + total);
//		
//		// Making use of session to store the processed request (i.e total, order, user data, etc)
//		HttpSession session = request.getSession();
//		session.setAttribute("total", total);
//		
//		// Using POST-Redirect-GET method to avoid multiple POST requests from the user
////		response.sendRedirect("/Receipt.html?total=" + total);
//		String redirectURL = "/Receipt.html";
//		redirectURL = response.encodeURL(redirectURL); //This creates a J Session ID in the URL
//		response.sendRedirect(redirectURL);
//		
//		out.close();
//	}
//}
