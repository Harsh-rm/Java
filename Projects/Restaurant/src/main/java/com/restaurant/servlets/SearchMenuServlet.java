package com.restaurant.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.data.MenuDao;
import com.restaurant.data.MenuDaoFactory;
import com.restaurant.domain.MenuItem;

@WebServlet("/searchResults.html")
public class SearchMenuServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		String searchTerm = request.getParameter("searchTerm");
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.find(searchTerm);
		
		request.setAttribute("menuItems", menuItems);
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatch = context.getRequestDispatcher("/searchResults.jsp");
		dispatch.forward(request, response);
		
//		if (menuItems.size() > 0) {
//			
//			out.println("<html><body><h1>Harsh's Restaurant</h1>");
//			out.println("<h2>Dishes containing " + searchTerm + "</h2><ul>");
//			for (MenuItem menuItem : menuItems) {
//				out.println("<li>" + menuItem + " " + menuItem.getDescription() + "</li>");
//			}
//			
//			out.println("</ul></body></html>");
//		}
//		else {
//			out.println("<html><body><h1>Harsh's Restaurant</h1>");
//			out.println("<p>I'm sorry, there are no dishes containing " + searchTerm);
//			out.println("</p></body></html>");
//		}
	}
}