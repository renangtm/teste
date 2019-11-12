package br.com.teste.TesteVaga.tratamentoerro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.teste.TesteVaga.conversores.FalhaConversaoException;

@RestControllerAdvice
public class TratamentoErroHandler {

	@Autowired
	private MessageSource source;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErroCampoDto> erroBeanValidation(MethodArgumentNotValidException exp) {

		return exp.getBindingResult().getFieldErrors().stream()
				.map(e -> new ErroCampoDto(e.getField(), source.getMessage(e, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());

	}

	@ExceptionHandler(FalhaConversaoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErroConversaoDto erroConversao(FalhaConversaoException exp) {

		return new ErroConversaoDto(exp.toString());

	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BancoInvalidoDto bancoInvalido(MethodArgumentTypeMismatchException exp) {
		
		return new BancoInvalidoDto(exp.getParameter().getParameterName()+" invalido");
		
	}
	
	

}
