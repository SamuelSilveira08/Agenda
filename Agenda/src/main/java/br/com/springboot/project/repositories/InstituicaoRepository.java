package br.com.springboot.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.project.entities.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
	
	List<Instituicao> findByNomeContaining(String nome);

}
