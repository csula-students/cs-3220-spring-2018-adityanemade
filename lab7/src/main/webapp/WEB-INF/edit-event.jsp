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
			<div class = "flex">
				<div class = "flex-form">
					<ul class = "form">
						<a class="link_buttons" href="events">Back</a>
						<%-- <form method = "POST" id = "edit_events" action = "editEvent"> --%>
							<form method = "POST" id = "edit_events" action = "edit">
								<input type="hidden" name="editForm" value="editEventForm"/>
             <h3>Edit Event</h3>
            <%-- <c:forEach var="e" items="${eventEdit}"> --%>
							<li><label for = "event_name">Event Name:</label></li>
							<li><input type="text" id = "event_name" name = "event_name" value="${ eventEdit.name }"></li>
							<li><label for = "description">Event Description</label></li>
							<li><textarea id = "description" name="description">${ eventEdit.description }</textarea></li>
							<li><label for = "trigger">Trigger at</label></li>
							<li><input type="number" id = "trigger" name="trigger" value="${ eventEdit.triggerAt }"></li>
							<li><button>Edit</button></li>
            <%-- </c:forEach> --%>
						</form>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
