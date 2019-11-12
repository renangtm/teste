package br.com.teste.TesteVaga.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.TesteVaga.conversores.FalhaConversaoException;
import br.com.teste.TesteVaga.domain.Instituicao;
import br.com.teste.TesteVaga.domain.Transferencia;
import br.com.teste.TesteVaga.dto.LinhaConvertidaDto;
import br.com.teste.TesteVaga.dto.TransferenciaDto;
import br.com.teste.TesteVaga.dto.TransferenciaViaLinhasForm;
import br.com.teste.TesteVaga.dto.TransferenciaViaLinhaForm;
import br.com.teste.TesteVaga.repository.TransferenciaRepository;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaRepository tr;

	@PostMapping("/lote")
	@Transactional
	@CacheEvict({ "cacheTransfPag" })
	public ResponseEntity<List<LinhaConvertidaDto>> receberTransferenciaUmaLinhas(
			@RequestBody @Valid TransferenciaViaLinhasForm form) throws FalhaConversaoException {

		List<Transferencia> transferencias = form.getTransferencias();

		transferencias.forEach(tr::save);

		return ResponseEntity.ok()
				.body(transferencias.stream().map(t -> t.getDestinatario().getConversor().paraTexto(t))
						.map(LinhaConvertidaDto::new).collect(Collectors.toList()));

	}

	@PostMapping("/unica")
	@Transactional
	@CacheEvict({ "cacheTransfPag" })
	public ResponseEntity<LinhaConvertidaDto> receberTransferenciaVariasLinha(
			@RequestBody @Valid TransferenciaViaLinhaForm form) throws FalhaConversaoException {

		Transferencia transferencia = form.getTransferencia();

		transferencia = tr.save(transferencia);

		return ResponseEntity.ok()
				.body(new LinhaConvertidaDto(transferencia.getDestinatario().getConversor().paraTexto(transferencia)));

	}

	@GetMapping("/paginada")
	@Transactional
	@Cacheable("cacheTransfPag")
	public ResponseEntity<Page<TransferenciaDto>> retornarTransferenciasPaginacao(Pageable paginacao,
			@RequestParam(required = true) Instituicao remetente) {

		Page<TransferenciaDto> transferencias = TransferenciaDto
				.converter(this.tr.findByRemetente(remetente, paginacao));

		return ResponseEntity.ok().body(transferencias);

	}

	/*
	 * Acredito que nao seja interessante fazer o cache nesse metodo porque se
	 * refere hipoteticamente a um numero muito grande de dados.
	 * 
	 * Mesmo nao tendo operacoes de modificacao no banco sempre
	 * coloco @Transactional porque na classe pode ter algum atributo do tipo Lazy
	 * que precisa da transacao aberta
	 */

	@GetMapping
	@Transactional
	public ResponseEntity<List<TransferenciaDto>> retornarTransferencias(
			@RequestParam(required = true) Instituicao remetente) {

		List<TransferenciaDto> transferencias = TransferenciaDto.converter(this.tr.findByRemetente(remetente));

		return ResponseEntity.ok().body(transferencias);

	}

}
