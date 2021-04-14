package com.nexti.crud.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem); 	// chama o construtor da superclasse runtimeException e passo a minha mensagem
	}
}
