var app = app || {};

app.imagens = (function ($) {
	function nova(url, callback) {
		if(!_verificaURL(url)) {
			alert("Apenas url's de gifs!");
			return;
		}
		_testaImagem(url, callback);
	}
	
	function _verificaURL(url) {
//	    return(url.match(/\.(jpeg|jpg|gif|png)$/) != null);
	    return(url.match(/\.(gif)$/) != null);
	}
	
	function adiciona(url, $container) {
		var $divDaImagem = $('<div>');
		var $imagem = $('<img>', {
			'src': url,
			'class': 'gif'
		});
		$divDaImagem.append($imagem).addClass('col-xs-4 col-sm-2 col-md-2 no-padding').appendTo($container);
	}
	
	function _testaImagem(url, callback) {
	    timeout = 10000;
	    var timedOut = false, timer;
	    var img = new Image();
	    img.onerror = img.onabort = function() {
	        if (!timedOut) {
	            clearTimeout(timer);
	            if(callback.hasOwnProperty('erro')) callback.erro(url);
	        }
	    };
	    img.onload = function() {
	        if (!timedOut) {
	            clearTimeout(timer);
	            if(callback.hasOwnProperty('sucesso')) callback.sucesso(url);
	        }
	    };
	    img.src = url;
	    timer = setTimeout(function() {
	        timedOut = true;
	        if(callback.hasOwnProperty('tempo')) callback.tempo(url);
	    }, timeout); 
	}
	
	return {
		nova: nova,
		adiciona: adiciona
	}
})(jQuery);

app.websocket = (function (imagens) {
	var websocket = {};
	var $containerImagens = {};
	function inicia($container) {
		$containerImagens = $container;
		websocket = new WebSocket('ws://localhost:8080/javaee-websockets/mensagem');
		websocket.onopen = function(evento) {
			_aoIniciar(evento);
        };
        websocket.onmessage = function(evento) {
        	_novaMensagem(evento);
        };
        websocket.onerror = function(evento) {
        	_erro(evento);
        };
	}
	function _conectado() {
		return websocket instanceof WebSocket;
	}
	function envia(url) {
		if(_conectado()) {
			imagens.nova(url, {
				'erro' : function (){
					alert('Imagem nao encontrada! Tente outra.');
				},
				'sucesso': function () {
					imagens.adiciona(url, $containerImagens);
					websocket.send(url);
				},
				'tempo': function () {
					alert('Imagem muito grande! Tente outra.');
				}
			});
		}
	}
	function _aoIniciar(evento) {
		console.info("Iniciou WS");
	}
	function _novaMensagem(evento) {
		var url = evento.data;
		imagens.nova(url, {
			'erro' : function (){
				console.error('Imagem nao encontrada!');
			},
			'sucesso': function () {
				imagens.adiciona(url, $containerImagens);
			},
			'tempo': function () {
				console.warn('Imagem muito grande! Não vai ser adicionada.');
			}
		});
	}
	function _erro(evento) {
		console.error("Erro no WS");
	}
	return {
		inicia: inicia,
		envia: envia
	}
})(app.imagens);