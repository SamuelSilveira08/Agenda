package br.com.springboot.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.project.entities.Aluno;
	
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	List<Aluno> findByNomeContaining(String nome);

}
