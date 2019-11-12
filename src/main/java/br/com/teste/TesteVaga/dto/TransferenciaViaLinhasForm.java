package br.com.teste.TesteVaga.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.teste.TesteVaga.conversores.FalhaConversaoException;
import br.com.teste.TesteVaga.domain.Instituicao;
import br.com.teste.TesteVaga.domain.Transferencia;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransferenciaViaLinhasForm {

	@NotEmpty
	private List<String> linhas;

	@NotNull
	private Instituicao remetente;

	@NotNull
	private Instituicao destinatario;

	public List<Transferencia> getTransferencias() throws FalhaConversaoException {

		List<Transferencia> transfs = new ArrayList<Transferencia>();

		for (String linha : this.linhas) {
			
			//nao utilizei operador map do Java 8 por conta da excecao que pode ser lancada
			Transferencia t = this.remetente.getConversor().paraTransferencia(linha);
			t.setRemetente(this.remetente);
			t.setDestinatario(this.destinatario);
			transfs.add(t);

		}

		return transfs;

	}

}
