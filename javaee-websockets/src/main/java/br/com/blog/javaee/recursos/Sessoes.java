package br.com.blog.javaee.recursos;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class Sessoes {
	private Set<Session> set = Collections.synchronizedSet(new HashSet<Session>());
	
	public boolean nova(Session sessao) {
		return set.add(sessao);
	}
	
	public boolean remove(Session sessao) {
		return set.remove(sessao);
	}

	public int ativas() {
		return set.size();
	}
}
