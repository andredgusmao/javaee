<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Websockets com JavaEE</title>
</head>
<body>
	<script type="text/javascript">
		var ws = new WebSocket('ws://localhost:8080/javaee-websockets/tag/${tag}');
	</script>

	<h3>Tag digitada: ${tag}</h3>
</body>
</html>
