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

import br.com.springboot.project.entities.Instituicao;
import br.com.springboot.project.repositories.InstituicaoRepository;

@Controller
@RequestMapping("/instituicoes")
public class InstituicaoController {
	
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("instituicao/index");
		List<Instituicao> instituicoes = instituicaoRepository.findAll();
		result.addObject("instituicoes", instituicoes);
		return result;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView result = new ModelAndView("instituicao/inserir");
		result.addObject("instituicao", new Instituicao());
		return result;
	}
	
	@PostMapping("/inserir")
	public String inserir(Instituicao instituicao) {
		instituicaoRepository.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Instituicao instituicao = instituicaoRepository.getOne(id);
		ModelAndView result = new ModelAndView("instituicao/editar");
		result.addObject("instituicao", instituicao);
		return result;
	}
	
	@PostMapping("/editar")
	public String editar(Instituicao instituicao) {
		instituicaoRepository.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		instituicaoRepository.deleteById(id);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome) {
		if(nome.isPresent()) {
			return instituicaoRepository.findByNomeContaining(nome.get());
		} else {
			return instituicaoRepository.findAll();
		}
	}

}
