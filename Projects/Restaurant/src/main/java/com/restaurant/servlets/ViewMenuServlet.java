package com.restaurant.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.data.MenuDao;
import com.restaurant.data.MenuDaoFactory;
import com.restaurant.domain.MenuItem;

//@WebServlet("")
public class ViewMenuServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();
		
		out.println("<html><body><h1>Harsh's Restaurant</h1>");
		out.println("<h2>Menu</h2><ul>");
		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "</li>");
		}
		out.println("</ul>");
		out.println("<a href='searchResults.html?searchTerm=veg' >View all the vegetarian dishes</a>");
		out.println("</body></html>");
		out.close();
	}
}