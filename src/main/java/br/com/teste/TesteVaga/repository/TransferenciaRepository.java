package br.com.teste.TesteVaga.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.TesteVaga.domain.Instituicao;
import br.com.teste.TesteVaga.domain.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Long>{

	public Page<Transferencia> findByRemetente(Instituicao instituicao,Pageable pageable);
	
	public List<Transferencia> findByRemetente(Instituicao instituicao);
	
}
