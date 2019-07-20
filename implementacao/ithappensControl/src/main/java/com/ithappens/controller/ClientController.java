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

import com.ithappens.model.Client;
import com.ithappens.model.User;
import com.ithappens.repository.Clients;


@Controller
@RequestMapping("/ithappens/client")
public class ClientController {
	
	private static final String CADASTRO_CLIENT_VIEW = "/pages/AlterarClient";
	private static final String CLIENT_VIEW = "/pages/ListarClient";
	
	@Autowired
	private Clients	clients;
	
	
	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_CLIENT_VIEW);
		mv.addObject(new Client());
		return mv;
	}
	
	// list
	@RequestMapping
	public ModelAndView lista() {
		List<Client> allClients = clients.findAll();
		ModelAndView mv = new ModelAndView(CLIENT_VIEW);
		mv.addObject(new User());
		mv.addObject("clients", allClients);
		return mv;
	}
	
	// Salvar 
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Client client, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_CLIENT_VIEW;
		}
		try {
			clients.save(client);
			attributes.addFlashAttribute("mensagem",
					"Cliente Salvo com sucesso!");
			return "redirect:/ithappens/client";
		} catch (IllegalArgumentException e) {
			return CADASTRO_CLIENT_VIEW;
		}
	}
	
	
	
	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Client client) {
		ModelAndView mv = new ModelAndView(CADASTRO_CLIENT_VIEW);
		mv.addObject(client);
		return mv;
	}
	
	
	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable Long codigo,
			@Validated Client client, Errors errors,
			RedirectAttributes attributes) {
		clients.delete(codigo);
		attributes.addFlashAttribute("mensagem",
				"Cliente excluído com sucesso!");
		return "redirect:/ithappens/client";
	}
	

}
