package com.ithappens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ithappens.model.Branch;
import com.ithappens.repository.Branchs;

@Controller
@RequestMapping("/ithappens/branch")
public class BranchController {
	
	private static final String CADASTRO_BRANCH_VIEW = "/pages/AlterarBranch";
	private static final String BRANCH_VIEW = "/pages/ListarBranch";

	
	@Autowired
	public Branchs branchs;
	
	
	
	// Cadastro Novo
		@RequestMapping("/novo")
		public ModelAndView novo() {
			ModelAndView mv = new ModelAndView(CADASTRO_BRANCH_VIEW);
			mv.addObject(new Branch());
			return mv;
		}
		
		// list
		@RequestMapping
		public ModelAndView lista() {
			List<Branch> allBranchs = branchs.findAll();
			ModelAndView mv = new ModelAndView(BRANCH_VIEW);
			mv.addObject(new Branch());
			mv.addObject("branchs", allBranchs);
			return mv;
		}
		
		
		// Salvar
		@RequestMapping(method = RequestMethod.POST)
		public String salvar(@Validated Branch branch, Errors errors,
				RedirectAttributes attributes) {
			if (errors.hasErrors()) {
				return CADASTRO_BRANCH_VIEW;
			}
			try {
				branchs.save(branch);
				attributes.addFlashAttribute("mensagem","Filial Salvo com sucesso!");
				return "redirect:/ithappens/branch";
			} catch (IllegalArgumentException e) {
				return CADASTRO_BRANCH_VIEW;
			}
		}
		
				
		
		// Editar
		@RequestMapping("{codigo}")
		public ModelAndView edicao(@PathVariable("codigo") Branch branch) {
			ModelAndView mv = new ModelAndView(CADASTRO_BRANCH_VIEW);
			mv.addObject(branch);
			return mv;
		}
		
		
		// Excluir
		@RequestMapping(value = "/delete/{codigo}")
		public String excluir(@PathVariable Long codigo,
				@Validated Branch branch, Errors errors,
				RedirectAttributes attributes) {
			branchs.delete(codigo);
			attributes.addFlashAttribute("mensagem","Filial excluído com sucesso!");
			return "redirect:/ithappens/branch";
		}
	
	

}
