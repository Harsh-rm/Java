package com.restaurant.data;

//This is not thread-safe and is used as a quick implementation for learning web development.

public class MenuDaoFactory {
	
	private static MenuDao menuDao;
	
	public static MenuDao getMenuDao() {
		if (menuDao == null) {
			menuDao = new MenuDao();
		}
		return menuDao;
	}

}