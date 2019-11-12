package br.com.teste.TesteVaga.conversores;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import br.com.teste.TesteVaga.domain.TipoTransacao;
import br.com.teste.TesteVaga.domain.Transferencia;
import br.com.teste.TesteVaga.utilidades.OperacoesComString;

public class ConversorBancoY implements ConversorTransferencia{


	public static void main(String[] args) throws Exception {
		
		ConversorBancoY cbx = new ConversorBancoY();
		
		String texto = "54561747150Antonio Alves da Silva        01140048510032569TED240317";
		
		Transferencia t = cbx.paraTransferencia(texto);
		
		System.out.println(t);
		
		System.out.println(cbx.paraTexto(t));
		
	}

	
	@Override
	public Transferencia paraTransferencia(String texto) throws FalhaConversaoException {
		
		try {
			
			List<String> dados = OperacoesComString.dividir(texto, Arrays.asList(11,30,4,6,7,3,6));
			
			return Transferencia
					.builder()
					.cpf(dados.get(0))
					.favorecido(dados.get(1))
					.agencia(dados.get(2))
					.conta(dados.get(3).substring(0,5))
					.digitoConta(dados.get(3).substring(5))
					.valor(new BigDecimal(dados.get(4)).divide(new BigDecimal(100)).setScale(2))
					.tipo(TipoTransacao.valueOf(dados.get(5)))
					.data(LocalDate.parse(dados.get(6),DateTimeFormatter.ofPattern("ddMMyy")))
					.build();
			
			
		}catch(Exception ex) {
			
			throw new FalhaConversaoException(texto);
			
		}
		
	}

	@Override
	public String paraTexto(Transferencia transf) {
		
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(OperacoesComString.limitarNumero(transf.getCpf(),11))
		.append(OperacoesComString.limitarTexto(transf.getFavorecido(),30))
		.append(OperacoesComString.limitarNumero(transf.getAgencia(),4))
		.append(OperacoesComString.limitarNumero(transf.getConta(),5))
		.append(OperacoesComString.limitarNumero(transf.getDigitoConta(),1))
		.append(OperacoesComString.limitarNumero(transf.getValor().toString().replaceAll("\\.", ""),7))
		.append(OperacoesComString.limitarTexto(transf.getTipo().toString(),3))
		.append(transf.getData().format(DateTimeFormatter.ofPattern("ddMMyy")));
		
		return sb.toString();
		
	}

}
