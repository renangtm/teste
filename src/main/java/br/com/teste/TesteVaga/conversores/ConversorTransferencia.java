package br.com.teste.TesteVaga.conversores;

import br.com.teste.TesteVaga.domain.Transferencia;

public interface ConversorTransferencia {
	
	public Transferencia paraTransferencia(String texto) throws FalhaConversaoException;
	
	public String paraTexto(Transferencia transf);

}
