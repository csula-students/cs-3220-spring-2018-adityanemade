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
			</div>
			<div class = "flex">
				<div class = "flex-form">
					<ul class = "form">
						<form method = "POST" id="auth-form" action = "auth">
             <h3>Login</h3>
<%-- if(request.getAttribute("error") == "errors"){ --%>
               <%-- <span id = "error">Incorrect Username/Password</span> --%>
<%-- } --%>
							<li><label for = "uname">Username:</label></li>
							<li><input type="text" id = "uname" name = "uname" required></li>
							<li><label for = "pwd">Password</label></li>
							<li><input type="password" id="pwd" name="pwd" required></li>
							<li><button>Login</button></li>
						</form>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
