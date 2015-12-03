package br.com.blog.javaee.recursos;

import java.util.Iterator;
import java.util.LinkedList;

public class Mensagens implements Iterable<String> {

	private LinkedList<String> lista;
	
	public Mensagens() {}
	
	public Mensagens(LinkedList<String> lista) {
		this.lista = lista;
	}
	
	@Override
	public Iterator<String> iterator() {
		return lista.iterator();
	}
	
	public void nova(String mensagem) {
		if(lista.size() > 15) {
			lista.pollLast();
		}		
		lista.addFirst(mensagem);
	}
}
