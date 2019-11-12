package br.com.teste.TesteVaga.domain;

import br.com.teste.TesteVaga.conversores.ConversorBancoX;
import br.com.teste.TesteVaga.conversores.ConversorBancoY;
import br.com.teste.TesteVaga.conversores.ConversorTransferencia;

public enum Instituicao {

	BANCOX("BANCOX", new ConversorBancoX()), 
	BANCOY("BANCOY", new ConversorBancoY());

	private String identificador;
	private ConversorTransferencia conversor;

	private Instituicao(String identificador, ConversorTransferencia conv) {

		this.identificador = identificador;
		this.conversor = conv;

	}

	public String getIdentificador() {
		return identificador;
	}

	public ConversorTransferencia getConversor() {
		return conversor;
	}

}
