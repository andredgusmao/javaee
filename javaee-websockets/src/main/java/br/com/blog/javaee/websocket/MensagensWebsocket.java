package br.com.blog.javaee.websocket;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.blog.javaee.recursos.Sessoes;

@ServerEndpoint("/mensagem")
public class MensagensWebsocket {
	
	@Inject private Sessoes sessoes;
	
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
