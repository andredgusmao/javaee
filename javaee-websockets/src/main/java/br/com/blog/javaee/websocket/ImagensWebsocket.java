package br.com.blog.javaee.websocket;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.blog.javaee.recursos.Sessoes;

@ServerEndpoint("/mensagem")
public class ImagensWebsocket {
	
	@Inject private Sessoes sessoes;
	
	@OnMessage
	public void novaMensagem(final Session sessao, final String mensagem) {
		System.out.println("novaMensagem " + mensagem);
		sessoes.broadcast(mensagem, sessao);
	}
	
	@OnOpen
	public void novaConexao(final Session sessao) {
		sessoes.nova(sessao);
		System.out.println("Nova sessão: " + sessao.getId() + "; Ativas: " + sessoes.ativas());
	}
	
	@OnClose
	public void desconectou(final Session sessao) {
		sessoes.remove(sessao);
		System.out.println("Sessão deconectou: " + sessao.getId() + "; Ativas: " + sessoes.ativas());
	}	
}
