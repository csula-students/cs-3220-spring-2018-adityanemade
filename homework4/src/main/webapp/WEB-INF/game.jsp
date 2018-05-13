<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Stress Reliever</title>
		<link rel="stylesheet" type="text/css" href="app.css">
		<link href="https://fonts.googleapis.com/css?family=Lobster|Nanum+Brush+Script" rel="stylesheet">
		<meta charset="UTF-8">
		<script>
			state = ${state};
			for (var i = 0; i < state.generators; i++) {
				var generator = state.generators[i];
				generator.type = 'autonomous';
	        	generator.quantity = 0;
	        	generator.unlockValue = generator.unlockAt;
	    	}
	    	for (var i = 0; i < state.story; i++) {
	    		var story = state.story[i];
	    		story.state = 'hidden';
	    		story.triggeredAt = story.triggerAt;
	    	}

	    	window.defaultState = state;
			</script>
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
		<script type="text/javascript" src="app.bundle.js"></script>
		<div class = "footer">
			<footer>&copy; Copyright 2018, Aditya Nemade</footer>
		</div>
	</body>
</html>
