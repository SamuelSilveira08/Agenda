package br.com.springboot.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@RequestMapping("/")
	public String hello() {
		return "redirect:/instituicoes/index";
	}

}
