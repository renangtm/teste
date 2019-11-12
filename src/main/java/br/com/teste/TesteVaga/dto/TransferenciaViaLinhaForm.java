package br.com.teste.TesteVaga.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.teste.TesteVaga.conversores.FalhaConversaoException;
import br.com.teste.TesteVaga.domain.Instituicao;
import br.com.teste.TesteVaga.domain.Transferencia;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransferenciaViaLinhaForm {

	@NotEmpty
	private String linha;

	@NotNull
	private Instituicao remetente;

	@NotNull
	private Instituicao destinatario;

	public Transferencia getTransferencia() throws FalhaConversaoException {

		Transferencia t = this.remetente.getConversor().paraTransferencia(this.linha);
		t.setRemetente(this.remetente);
		t.setDestinatario(this.destinatario);

		return t;

	}

}
