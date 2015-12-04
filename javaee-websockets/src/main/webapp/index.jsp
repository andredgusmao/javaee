<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Websockets com JavaEE</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/estilo.css"/>"/>
</head>
<body>
	<h3>Index - Bem vindo</h3>
	<section id="form">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-4">
				<input type="text" id="gif-url" />
			</div>
			<div class="col-md-2">
				<button id="envia-gif" class="btn btn-info">Enviar</button>	
			</div>
			<div class="col-md-3"></div>
		</div>
	</section>
	<section id="imagens">
		<div id="container-imagens" class="row">
		</div>
	</section>
	
	<script src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
	<script src="<c:url value="/resources/js/app.js"/>"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var $container = $('#container-imagens');
			app.websocket.inicia($container);
			
			$('#envia-gif').on('click', function () {
				var urlDaImagem = $('#gif-url').val();
				app.websocket.envia(urlDaImagem);
				$('#gif-url').val('');
			});
		});
	</script>
</body>
</html>

