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

import com.ithappens.model.OrderedItem;
import com.ithappens.model.Sale;
import com.ithappens.model.User;
import com.ithappens.repository.OrderedItems;
import com.ithappens.repository.Users;

@Controller
@RequestMapping("/ithappens/orderedItems")
public class OrderedItemController {

	private static final String LIST_ORDEREDITEMS_VIEW = "/pages/ListarOrderedItems";
	private static final String ALTER_ORDEREDITEMS_VIEW = "/pages/AlterarOrderedItems";

	@Autowired
	private OrderedItems orderedItems;

	@Autowired
	private Users users;

	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated OrderedItem orderedItem, Sale sale, Errors errors, RedirectAttributes attributes) {
		attributes.addAttribute(sale);
		if (errors.hasErrors()) {
			return "redirect:/ithappens/detalhes/" + orderedItem.getSales().getCodigo();
		}
		try {
			orderedItems.save(orderedItem);
			attributes.addFlashAttribute("mensagem", "Pedido adicionado a venda!");
			return "redirect:/ithappens/detalhes/" + orderedItem.getSales().getCodigo();
		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "redirect:/ithappens/detalhes/" + orderedItem.getSales().getCodigo();
		}
	}

	// list
	@RequestMapping
	public ModelAndView lista() {
		List<OrderedItem> allOrderedItems = orderedItems.findAll();
		ModelAndView mv = new ModelAndView(LIST_ORDEREDITEMS_VIEW);
		mv.addObject(new OrderedItem());
		mv.addObject("orderedItems", allOrderedItems);
		return mv;
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") OrderedItem orderedItem) {
		ModelAndView mv = new ModelAndView(ALTER_ORDEREDITEMS_VIEW);
		mv.addObject(orderedItem);
		List<User> allUsers = users.findAll();
		mv.addObject(new User());
		mv.addObject("tdusers", allUsers);
		return mv;
	}

	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable("codigo") OrderedItem orderedItem) {
		orderedItems.delete(orderedItem);
		return "redirect:/ithappens/detalhes/" + orderedItem.getSales().getCodigo();
	}

}
