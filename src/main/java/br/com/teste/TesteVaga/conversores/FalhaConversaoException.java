package br.com.teste.TesteVaga.conversores;

public class FalhaConversaoException extends Exception {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public FalhaConversaoException(String texto) {

		super("Ocorreu um problema na conversao do texto '" + texto + "'");

	}

}
