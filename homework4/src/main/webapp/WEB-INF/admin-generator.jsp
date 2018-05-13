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
						<form method = "POST" id="generators_form" action = "generators">
             <h3>Add Generator</h3>
							<li><label for = "generator_name">Generator Name:</label></li>
							<li><input type="text" id = "generator_name" name = "generator_name" required></li>
							<li><label for = "generator_rate">Generator Rate:</label></li>
							<li><input type="number" id = "generator_rate" name = "generator_rate" required></li>
							<li><label for = "generator_base">Base Cost:</label></li>
							<li><input type="number" id = "generator_base" name = "generator_base" required></li>
							<li><label for = "generator_unlock">Unlock at:</label></li>
							<li><input type="number" id = "generator_unlock" name = "generator_unlock" required></li>
							<li><label for = "description">Event Description</label></li>
							<li><textarea id = "generator_description" name="generator_description" required></textarea></li>
							<li><button>Add</button></li>
						</form>
					</ul>
				</div>
				<div class = "table">
					<table>
						<tr>
							<th>Name</th>
							<th>Rate</th>
							<th>Cost</th>
							<th>Unlock at</th>
							<th>Description</th>
						</tr>
          <c:forEach var="g" items="${generatorData}">
  					<tr>
    					<td>${ g.name }</td>
    					<td>${ g.rate }</td>
    					<td>${ g.baseCost }</td>
    					<td>${ g.unlockAt }</td>
    					<td>${ g.description }</td>
    					<td><a class = "link_buttons" href="edit?set_Id=${ g.id }">Edit</a>
							<%-- <td><a class = "link_buttons" href="editGenerator?set_Id=${ g.id }">Edit</a> --%>
                |<a class = "link_buttons" href="delete?delete_Id=${ g.id }">Delete</a></td>
  					</tr>
          </c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
