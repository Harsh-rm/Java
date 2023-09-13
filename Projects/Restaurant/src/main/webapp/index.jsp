<%@ page import="java.util.List"%>
<!-- @ page is called directive in JSP -->
<%@ page import="com.restaurant.data.MenuDao"%>
<%@ page import="com.restaurant.data.MenuDaoFactory"%>
<%@ page import="com.restaurant.domain.MenuItem"%>
<%@ page import="com.restaurant.domain.MenuCategory"%>

<!DOCTYPE html>
<html>

<head>
<title>Welcome | Menu</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body, h1, h5 {
	font-family: "Raleway", sans-serif
}

body, html {
	height: 100%
}

.bgimg {
	background-image: url('blog-img.jpg');
	background-repeat: no-repeat;
	min-height: 100%;
	background-position: center;
	background-size: cover;
}
</style>
</head>

<body>

	<div class="bgimg w3-display-container w3-text-black">
		<div class="w3-display-topleft w3-jumbo">
			<p>Welcome to Harsh's Cafe</p>
		</div>
		<div class="w3-display-middle w3-container w3-xxxlarge">
			<p>
				<button
					onclick="document.getElementById('menu').style.display='block'"
					class="w3-button w3-black">Menu</button>
			</p>
		</div>
		<div class="w3-display-bottomright w3-container w3-xlarge">
			<p>
				<button
					onclick="document.getElementById('contact').style.display='block'"
					class="w3-button w3-black">Contact</button>
			</p>
		</div>
		<div class="w3-display-bottomleft w3-container">
			<p class="w3-xlarge">Monday - Friday 8am - 6pm | Weekends 9am -
				5pm</p>
			<p class="w3-large">42 Village St, Fremont, California - 94539</p>
			<p>
				powered by <a href="https://www.w3schools.com/w3css/default.asp"
					target="_blank">w3.css</a>
			</p>
		</div>
	</div>

	<!-- Menu Modal -->
	<div id="menu" class="w3-modal">
		<div class="w3-modal-content w3-animate-zoom">
			<%
			MenuDao menuDao = MenuDaoFactory.getMenuDao();
			List<MenuItem> menuItems = menuDao.getFullMenu();
			%>
			<div class="w3-container w3-black w3-display-container">
				<span onclick="document.getElementById('menu').style.display='none'"
					class="w3-button w3-display-topright w3-large">x</span>
				<%
				for (MenuCategory category : MenuCategory.values()) {
				%>
			</div>

			<div class="w3-container w3-black ">
				<h1><%=category%></h1>
			</div>

			<div class="w3-container">
				<%
				for (MenuItem menuItem : menuItems) {
					if (menuItem.getCategory().toString().equals(category.toString())) {
						out.println("<h5>" + menuItem.getName() + " $" + "<b>" + menuItem.getPrice() + "</b></h5>");
					}
				}
				}
				%>
			</div>
		</div>
	</div>

	<!-- Contact Modal -->
	<div id="contact" class="w3-modal">
		<div class="w3-modal-content w3-animate-zoom">
			<div class="w3-container w3-black">
				<span
					onclick="document.getElementById('contact').style.display='none'"
					class="w3-button w3-display-topright w3-large">x</span>
				<h1>Contact</h1>
			</div>
			<div class="w3-container">
				<p>Reserve a table, ask for today's special or just send us a
					message:</p>
				<form action="/action_page.php" target="_blank">
					<p>
						<input class="w3-input w3-padding-16 w3-border" type="text"
							placeholder="Name" required name="Name">
					</p>
					<p>
						<input class="w3-input w3-padding-16 w3-border" type="number"
							placeholder="How many people" required name="People">
					</p>
					<p>
						<input class="w3-input w3-padding-16 w3-border"
							type="datetime-local" placeholder="Date and time" required
							name="date" value="2020-11-16T20:00">
					</p>
					<p>
						<input class="w3-input w3-padding-16 w3-border" type="text"
							placeholder="Message \ Special requirements" required
							name="Message">
					</p>
					<p>
						<button class="w3-button" type="submit">SEND MESSAGE</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>