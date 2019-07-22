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

import com.ithappens.model.Product;
import com.ithappens.repository.Products;

@Controller
@RequestMapping("/ithappens/product")
public class ProductController {

	private static final String CADASTRO_PRODUCT_VIEW = "/pages/AlterarProduct";
	private static final String PRODUCT_VIEW = "/pages/ListarProduct";

	@Autowired
	public Products products;

	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_PRODUCT_VIEW);
		mv.addObject(new Product());
		return mv;
	}

	// list
	@RequestMapping
	public ModelAndView lista() {
		List<Product> allProducts = products.findAll();
		ModelAndView mv = new ModelAndView(PRODUCT_VIEW);
		mv.addObject(new Product());
		mv.addObject("products", allProducts);
		return mv;
	}

	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Product product, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_PRODUCT_VIEW;
		}
		try {
			products.save(product);
			attributes.addFlashAttribute("mensagem", "Produto Salvo com sucesso!");
			return "redirect:/ithappens/product";
		} catch (IllegalArgumentException e) {
			return CADASTRO_PRODUCT_VIEW;
		}
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Product product) {
		ModelAndView mv = new ModelAndView(CADASTRO_PRODUCT_VIEW);
		mv.addObject(product);
		return mv;
	}

	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable Long codigo, @Validated Product product, Errors errors,
			RedirectAttributes attributes) {
		products.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
		return "redirect:/ithappens/product";
	}

}
