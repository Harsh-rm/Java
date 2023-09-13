package com.virtualpairprogrammers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.virtualpairprogrammers.domain.MenuCategory;
import com.virtualpairprogrammers.domain.MenuItem;
import com.virtualpairprogrammers.domain.Order;

public class MenuDao {

	public MenuDao() {
		DatabaseBootstrap bootstrap = new DatabaseBootstrap();
		bootstrap.initializeDatabase();
	}

	private List<MenuItem> buildMenu(ResultSet results) throws SQLException {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		while (results.next()) {
			MenuItem menuItem = new MenuItem();
			menuItem.setId(results.getInt("id"));
			menuItem.setDescription(results.getString("description"));
			menuItem.setName(results.getString("name"));
			menuItem.setPrice(results.getDouble("price"));
			menuItem.setCategory(MenuCategory.valueOf(results.getString("category")));
			menuItems.add(menuItem);
		}
		return menuItems;
	}

	public List<MenuItem>  getFullMenu() {
		List<MenuItem> menuItems = null;
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				Statement stm = conn.createStatement();
				ResultSet results = stm.executeQuery("SELECT * FROM menuitems");
				) {	
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return menuItems;
	}

	public List<MenuItem> find(String searchString) {
		List<MenuItem> menuItems = null;
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				PreparedStatement stm = conn.prepareStatement("SELECT * FROM menuitems WHERE name LIKE ? OR description LIKE ?");
				) {	

			stm.setString(1, "%" + searchString + "%");
			stm.setString(2, "%" + searchString + "%");

			ResultSet results = stm.executeQuery();
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return menuItems;
	}

	public MenuItem getItem(int id) {
		List<MenuItem> menuItems = null;
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				PreparedStatement stm = conn.prepareStatement("SELECT * FROM menuitems WHERE id = ?");
				) {	

			stm.setInt(1, id);

			ResultSet results = stm.executeQuery();
			menuItems = buildMenu(results);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return menuItems.get(0);
	}


	public Order newOrder(String customer) {
		Order order = new Order(); 
		order.setStatus("pending");
		order.setCustomer(customer);
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				PreparedStatement stm = conn.prepareStatement("INSERT INTO orders (status, customer) values (?,?)", Statement.RETURN_GENERATED_KEYS);
				) {	
			stm.setString(1, order.getStatus());
			stm.setString(2,  order.getCustomer());
			stm.execute();
			
			try(ResultSet generatedKeys = stm.getGeneratedKeys()) {
				generatedKeys.next();
		        order.setId(generatedKeys.getLong(1));		        
		    }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return order;
	}

	private Map<MenuItem,Integer> convertContentsToOrderMap(String contents) {
		Map<MenuItem,Integer> orderMap = new HashMap<MenuItem,Integer>();
		if (contents == null || contents.equals("")) {
			return orderMap;
		}
			String[] items = contents.split(":");
			for (int i = 0; i < items.length; i++) {
				String key = items[i].split(",")[0];
				String value = items[i].split(",")[1];
				MenuItem menuItem = getItem(Integer.valueOf(key));
				orderMap.put(menuItem, Integer.valueOf(value));
			}
		return orderMap;
	}

	private String convertOrderMapToContents(Map<MenuItem,Integer> orderMap) {
		String contents = "";
		if (orderMap.keySet().size() == 0) {
			return contents;
		}
		for (MenuItem menuItem : orderMap.keySet() ) {
			contents = contents + menuItem.getId() + "," + orderMap.get(menuItem) + ":";
		}
		contents = contents.substring(0, contents.length()-1);
		return contents;
	}

	public void addToOrder(Long id, MenuItem menuItem, int quantity) {
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("SELECT * FROM orders WHERE ID = " + id);
				PreparedStatement stmUpdate = conn.prepareStatement("UPDATE orders SET contents = ? WHERE id = ?");
				) {	
			res.next();
			String contents = res.getString("contents");
			Map<MenuItem, Integer> orderMap = convertContentsToOrderMap(contents);
			if (orderMap.get(menuItem) != null) {
				orderMap.put(menuItem, orderMap.get(menuItem) + quantity);
			}
			else {
				orderMap.put(menuItem, quantity);
			}
			contents = convertOrderMapToContents(orderMap);
			stmUpdate.setString(1, contents);
			stmUpdate.setLong(2, id);
			stmUpdate.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Double getOrderTotal(Long id) {
		double d = 0d;
		try (	Connection conn = DriverManager.getConnection("jdbc:h2:~/restaurant","","");
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("SELECT * FROM orders WHERE id = " + id);
				) {
			res.next();
			Map<MenuItem,Integer> orderMap = convertContentsToOrderMap(res.getString("contents"));
			for (MenuItem menuItem : orderMap.keySet()) {
				d += menuItem.getPrice() * orderMap.get(menuItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return d;
	}
}
