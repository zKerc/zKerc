package tad.listasEncadeadas;

import java.lang.reflect.Array;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	//TODO: implementar o nó cabeça
	//TODO: implementar o nó cauda 
	//TODO: implementar as sentinelas
	
	NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);
		
		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);
		
	}

	@Override
	public boolean isEmpty() {
	    return cabeca.getProximo() == cauda;
	}

	@Override
	public int size() {
	    int size = 0;
	    NodoListaDuplamenteEncadeada<T> current = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    while (current != cauda) {
	        size++;
	        current = (NodoListaDuplamenteEncadeada<T>) current.getProximo();
	    }
	    return size;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
	    NodoListaDuplamenteEncadeada<T> current = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    while (current != cauda) {
	        if (current.getChave().equals(chave)) {
	            return current;
	        }
	        current = (NodoListaDuplamenteEncadeada<T>) current.getProximo();
	    }
	    return null;
	}

	@Override
	public void insert(T chave) {
		//1. Craiar o novo registro
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
		
		//2. Inserir o novo nó na lista
		
		novoNo.setProximo(cabeca.getProximo());
		((NodoListaDuplamenteEncadeada<T>) cabeca.getProximo()).setAnterior(novoNo);
		novoNo.setAnterior(cabeca);
		cabeca.setProximo(novoNo);
			
		
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) {
	    NodoListaDuplamenteEncadeada<T> nodeToRemove = search(chave);
	    if (nodeToRemove != null) {
	        nodeToRemove.getAnterior().setProximo(nodeToRemove.getProximo());
	        nodeToRemove.getProximo().setAnterior(nodeToRemove.getAnterior());
	        return nodeToRemove;
	    }
	    return null;
	}

	@Override
	public String imprimeEmOrdem() {
	    StringBuilder sb = new StringBuilder();
	    NodoListaDuplamenteEncadeada<T> current = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    while (current != cauda) {
	        sb.append(current.getChave());
	        current = (NodoListaDuplamenteEncadeada<T>) current.getProximo();
	        if (current != cauda) {
	            sb.append(", ");
	        }
	    }
	    return sb.toString();
	}

	@Override
	public String imprimeInverso() {
	    StringBuilder sb = new StringBuilder();
	    NodoListaDuplamenteEncadeada<T> current = cauda.getAnterior();
	    while (current != cabeca) {
	        sb.append(current.getChave());
	        current = current.getAnterior();
	        if (current != cabeca) {
	            sb.append(", ");
	        }
	    }
	    return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
	    T[] array = (T[]) Array.newInstance(clazz, size());
	    NodoListaDuplamenteEncadeada<T> current = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    int i = 0;
	    while (current != cauda) {
	        array[i++] = current.getChave();
	        current = (NodoListaDuplamenteEncadeada<T>) current.getProximo();
	    }
	    return array;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
		
	}

	

}
