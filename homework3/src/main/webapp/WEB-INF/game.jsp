<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Stress Reliever</title>
		<link rel="stylesheet" type="text/css" href="../app.css">
		<link href="https://fonts.googleapis.com/css?family=Lobster|Nanum+Brush+Script" rel="stylesheet">
		<meta charset="UTF-8">
	</head>
	<body>
		<game-story-book></game-story-book>
		<div class = "game-resource">
			<game-counter></game-counter>
			<game-button></game-button>
		</div>
		<game-generator></game-generator>
    <script>
		  window.game = {
		      state: {
		          counter: 0
          }
		  };
		  window.game.state = ${state};
		</script>
		<script type="text/javascript" src="../app.bundle.js"></script>
		<div class = "footer">
			<footer>&copy; Copyright 2018, Aditya Nemade</footer>
		</div>
	</body>
</html>
