<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
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
						<form method = "POST" id="events_form" action = "events">
             <h3>Add Event</h3>
							<li><label for = "event_name">Event Name:</label></li>
							<li><input type="text" id = "event_name" name = "event_name" required></li>
							<li><label for = "description">Event Description</label></li>
							<li><textarea id = "event_description" name="event_description" required></textarea></li>
							<li><label for = "trigger">Trigger at</label></li>
							<li><input type="number" id="trigger" name="trigger" required></li>
							<li><button>Add</button></li>
						</form>
					</ul>
				</div>
				<div class = "table">
					<table>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Trigger At</th>
							<th>Actions</th>
						</tr>
  				<c:forEach var="e" items="${eventData}">
  					<tr>
    					<td>${ e.name }</td>
    					<td>${ e.description }</td>
    					<td>${ e.triggerAt }</td>
    					<td><a class = "link_buttons" href='edit?set_Id=${ e.id }'>Edit</a>
							<%-- <td><a class = "link_buttons" href='editEvent?set_Id=${ e.id }'>Edit</a> --%>
                |<a class = "link_buttons" href='delete?delete_Id=${ e.id }'>Delete</a></td>
  					</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
