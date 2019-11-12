package br.com.teste.TesteVaga.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperacoesComString {

	private OperacoesComString() {

	}

	public static String limitarNumero(String valor, int casas) {

		while (valor.length() < casas)
			valor = '0' + valor;

		while (valor.length() > casas)
			valor = valor.substring(1);

		return valor;

	}

	public static String limitarTexto(String valor, int casas) {

		while (valor.length() < casas)
			valor = valor + ' ';

		while (valor.length() > casas)
			valor = valor.substring(0, valor.length() - 1);

		return valor;

	}

	public static List<String> dividir(String texto, List<Integer> partes) {

		if (partes.stream().mapToInt(i -> i).sum() > texto.length())
			throw new RuntimeException();

		List<String> resultado = new ArrayList<String>();

		int anterior = 0;

		for (Integer i : partes) {
			resultado.add(texto.substring(anterior, anterior + i));
			anterior += i;
		}

		return resultado.stream().map(r -> {

			// retirando espacos fim;
			while (r.length() > 0 && r.charAt(r.length() - 1) == ' ') {
				r = r.substring(0, r.length() - 1);
			}

			// retirando espacos comeco
			while (r.length() > 0 && r.charAt(0) == ' ') {
				r = r.substring(1);
			}

			return r;

		}).collect(Collectors.toList());

	}

}
