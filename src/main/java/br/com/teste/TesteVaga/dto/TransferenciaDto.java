package br.com.teste.TesteVaga.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.teste.TesteVaga.domain.Instituicao;
import br.com.teste.TesteVaga.domain.Transferencia;
import lombok.Getter;

/*
 * Nesse caso a classe DTO é identica a classe de dominio, porem pensando em um possivel crescimento acho interessante separar
 */

@Getter
public class TransferenciaDto {

	public static Page<TransferenciaDto> converter(Page<Transferencia> transferencias){
		
		return transferencias.map(TransferenciaDto::new);
		
	}
	
	public static List<TransferenciaDto> converter(List<Transferencia> transferencias){
		
		return transferencias.stream().map(TransferenciaDto::new).collect(Collectors.toList());
		
	}
	
	private Long id;
	private Instituicao remetente;
	private Instituicao destinatario;
	private BigDecimal valor;
	private LocalDate data;
	private String cpf;
	private String conta;
	private String agencia;
	private String digitoConta;
	private String favorecido;

	public TransferenciaDto(Transferencia t) {

		this.id = t.getId();
		this.remetente = t.getRemetente();
		this.destinatario = t.getDestinatario();
		this.valor = t.getValor();
		this.data = t.getData();
		this.cpf = t.getCpf();
		this.conta = t.getConta();
		this.agencia = t.getAgencia();
		this.digitoConta = t.getDigitoConta();
		this.favorecido = t.getFavorecido();

	}

}
