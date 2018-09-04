package com.crudpmz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crudpmz.models.Aluno;
import com.crudpmz.repository.AlunoRepository;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoRepository er;
	
	@RequestMapping(value="/CadastrarAluno", method=RequestMethod.GET)
	public String form(){
		return "aluno/formCadAluno";
	}
	
	@RequestMapping(value="/CadastrarAluno", method=RequestMethod.POST)
	public String form(Aluno aluno){
		
		er.save(aluno);
		
		return "redirect:/CadastrarAluno";
	}
	
	@RequestMapping("/alunos")
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Aluno> alunos = er.findAll();
		mv.addObject("alunos",alunos);
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView buscarAluno(@PathVariable("codigo") long codigo) {
		
		Aluno aluno = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("aluno/formEditAluno");
		mv.addObject("aluno",aluno);
		return mv;
		
	}
	
	@RequestMapping("/deletar")
	public String deletarAluno(long codigo) {
		Aluno aluno = er.findByCodigo(codigo);
		er.delete(aluno);
		
		return "redirect:/alunos";
	}
}
