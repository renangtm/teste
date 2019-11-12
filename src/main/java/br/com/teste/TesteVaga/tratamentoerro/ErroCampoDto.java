package br.com.teste.TesteVaga.tratamentoerro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroCampoDto {
	
	private String campo;
	private String erro;

}
