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
						<a class="link_buttons" href="generators">Back</a>
						<%-- <form method = "POST" id="edit-generators" action = "editGenerator"> --%>
						<form method = "POST" id="edit-generators" action = "edit">
							<input type="hidden" name="editForm" value="editGeneratorForm"/>
             <h3>Add Generator</h3>
            <%-- <c:forEach var="g" items="${generatorEdit}"> --%>
							<li><label for = "generator_name">Generator Name:</label></li>
							<li><input type="text" id = "generator_name" name = "generator_name" value="${ generatorEdit.name }" required></li>
							<li><label for = "generator_rate">Generator Rate:</label></li>
							<li><input type="number" id = "generator_rate" name = "generator_rate" value="${ generatorEdit.rate }" required></li>
							<li><label for = "generator_base">Base Cost:</label></li>
							<li><input type="number" id = "generator_base" name = "generator_base"value="${ generatorEdit.baseCost }" required></li>
							<li><label for = "generator_unlock">Unlock at:</label></li>
							<li><input type="number" id = "generator_unlock" name = "generator_unlock" value="${ generatorEdit.unlockAt }" required></li>
							<li><label for = "description">Event Description</label></li>
							<li><textarea id = "generator_description" name="generator_description" required>${ generatorEdit.description }"</textarea></li>
							<li><button>Edit</button></li>
            <%-- </c:forEach> --%>
						</form>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
