package br.com.springboot.project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.project.entities.Aluno;
import br.com.springboot.project.entities.Instituicao;
import br.com.springboot.project.repositories.AlunoRepository;
import br.com.springboot.project.repositories.InstituicaoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepo;
	
	@Autowired
	private InstituicaoRepository instituicaoRepo;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("aluno/index");
		result.addObject("alunos", alunoRepo.findAll());
		return result;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView result = new ModelAndView("aluno/inserir");
		Aluno aluno = new Aluno();
		aluno.setInstituicao(new Instituicao());
		result.addObject("aluno", aluno);
		result.addObject("instituicoes", instituicaoRepo.findAll());
		return result;
	}
	
	@PostMapping("/inserir")
	public String inserir(Aluno aluno) {
		alunoRepo.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("aluno/editar");
		Aluno aluno = alunoRepo.getOne(id);
		result.addObject("aluno", aluno);
		result.addObject("instituicoes", instituicaoRepo.findAll());
		return result;
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno) {
		alunoRepo.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		alunoRepo.deleteById(id);
		return "redirect:/alunos/index";
	}
	
	@GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Aluno> pesquisarPorNome(@PathVariable Optional<String> nome) {
		if(nome.isPresent()) {
			return alunoRepo.findByNomeContaining(nome.get());
		} else {
			return alunoRepo.findAll();
		}
	}
	
}
