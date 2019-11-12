package br.com.teste.TesteVaga.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Transferencia {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column @Enumerated(EnumType.STRING)
	private Instituicao remetente;
	
	@Column @Enumerated(EnumType.STRING)
	private Instituicao destinatario;
	
	@Column
	private String cpf;
	
	@Column @Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	@Column
	private String conta;
	
	@Column
	private String agencia;
	
	@Column
	private String digitoConta;
	
	@Column
	private String favorecido;
	
	@Column
	private BigDecimal valor;
	
	@Column
	private LocalDate data;

}
