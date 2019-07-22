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

import com.ithappens.model.User;
import com.ithappens.model.Sale;
import com.ithappens.repository.Users;

@Controller
@RequestMapping("/ithappens/user")
public class UserController {

	private static final String CADASTRO_USER_VIEW = "/pages/AlterarUser";
	private static final String USER_VIEW = "/pages/ListarUser";

	@Autowired
	private Users users;

	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_USER_VIEW);
		mv.addObject(new User());
		return mv;
	}

	// list
	@RequestMapping
	public ModelAndView lista() {
		List<User> allUsers = users.findAll();
		ModelAndView mv = new ModelAndView(USER_VIEW);
		mv.addObject(new User());
		mv.addObject("users", allUsers);
		return mv;
	}

	// Salvar Usuário
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated User user, Sale sale, Errors errors, RedirectAttributes attributes) {
		attributes.addAttribute(sale);
		if (errors.hasErrors()) {
			return CADASTRO_USER_VIEW;
		}
		try {
			users.save(user);
			attributes.addFlashAttribute("mensagem", "Usuário Salvo com sucesso!");
			return "redirect:/ithappens/user";
		} catch (IllegalArgumentException e) {
			return CADASTRO_USER_VIEW;
		}
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") User user) {
		ModelAndView mv = new ModelAndView(CADASTRO_USER_VIEW);
		mv.addObject(user);
		return mv;
	}

	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable Long codigo, @Validated User user, Errors errors,
			RedirectAttributes attributes) {
		users.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
		return "redirect:/ithappens/user";
	}

}
