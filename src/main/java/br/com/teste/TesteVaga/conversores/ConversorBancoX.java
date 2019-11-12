package br.com.teste.TesteVaga.conversores;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import br.com.teste.TesteVaga.domain.TipoTransacao;
import br.com.teste.TesteVaga.domain.Transferencia;
import br.com.teste.TesteVaga.utilidades.OperacoesComString;

public class ConversorBancoX implements ConversorTransferencia{

	public static void main(String[] args) throws Exception {
		
		ConversorBancoX cbx = new ConversorBancoX();
		
		String texto = "TED0114004851Antonio Alves da Silva        54561747150003256924032017";
		
		Transferencia t = cbx.paraTransferencia(texto);
		
		System.out.println(t);
		
		System.out.println(cbx.paraTexto(t));
		
	}

	
	@Override
	public Transferencia paraTransferencia(String texto) throws FalhaConversaoException {
		
		try {
			
			List<String> dados = OperacoesComString.dividir(texto, Arrays.asList(3,4,5,1,30,11,5,2,8));
			
			return Transferencia
					.builder()
					.tipo(TipoTransacao.valueOf(dados.get(0)))
					.agencia(dados.get(1))
					.conta(dados.get(2))
					.digitoConta(dados.get(3))
					.favorecido(dados.get(4))
					.cpf(dados.get(5))
					.valor(new BigDecimal(dados.get(6)+"."+dados.get(7)))
					.data(LocalDate.parse(dados.get(8), DateTimeFormatter.ofPattern("ddMMyyyy")))
					.build();
			
			
		}catch(Exception ex) {
			
			throw new FalhaConversaoException(texto);
			
		}
		
	}

	@Override
	public String paraTexto(Transferencia transf) {
		
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(OperacoesComString.limitarTexto(transf.getTipo().toString(),3))
		.append(OperacoesComString.limitarNumero(transf.getAgencia(),4))
		.append(OperacoesComString.limitarNumero(transf.getConta(),5))
		.append(OperacoesComString.limitarNumero(transf.getDigitoConta(),1))
		.append(OperacoesComString.limitarTexto(transf.getFavorecido().toString(),30))
		.append(OperacoesComString.limitarNumero(transf.getCpf(),11))
		.append(OperacoesComString.limitarNumero(transf.getValor().toString().replaceAll("\\.", ""),7))
		.append(transf.getData().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
		
		return sb.toString();
		
	}

}
