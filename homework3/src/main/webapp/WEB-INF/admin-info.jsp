<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Incremental Game</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
	</head>
	<body>
		<div class="main">
			<div class="header">
				<header><h1>Incremental Game Framework</h1></header>
				<form method = "DELETE" id = "logout-form" actions = "auth" >
					<button id="logout-btn">Logout</button>
				</form>
			</div>
			<div class="nav">
				<nav>
					<a id="a1" href="info">Game Information</a> |
					<a id="a2" href="generators">Generators</a> |
					<a id="a3" href="events">Events</a>
				</nav>
			</div>
  		<div class = "flex">
  			<div class = "flex-form">
  				<ul class = "form">
            <form method = "POST" id="info-form" action = "info">
              <li><h3>Game Name</h3></li>
  					  <li><input type="text" id = "game_name" name = "game_name" required></li>
  					  <li><button id="btn_save" name="btn_save">Save</button></li>
            </form>
  				</ul>
  			</div>
  		</div>
  	</div>
  </body>
</html>
