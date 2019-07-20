package com.ithappens.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ithappens.model.Branch;
import com.ithappens.model.Client;
import com.ithappens.model.OrderedItem;
import com.ithappens.model.Sale;
import com.ithappens.model.Status;
import com.ithappens.model.TypeRecipe;
import com.ithappens.model.User;
import com.ithappens.repository.Branchs;
import com.ithappens.repository.Clients;
import com.ithappens.repository.OrderedItems;
import com.ithappens.repository.Users;
import com.ithappens.repository.filter.SaleFilter;
import com.ithappens.service.CadastroSaleService;

@Controller
@RequestMapping("/ithappens")
public class SaleController {

	private static final String CADASTRO_VIEW = "/pages/CadastroSale";
	private static final String LIST_SALE_VIEW = "/pages/ListarSale";
	private static final String DETALHE_SALE_VIEW = "/pages/DetalheSale";

	@Autowired
	private CadastroSaleService cadastroSaleservice;

	@Autowired
	private Users users;

	@Autowired
	private Clients clients;

	@Autowired
	private Branchs branchs;

	@Autowired
	private OrderedItems orderedItems;

	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro") SaleFilter filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Sale> allSale = cadastroSaleservice.filtrar(filtro);
		mv.addObject("sales", allSale);
		mv.addObject(new Sale());
		return mv;
	}

	// Combo Users
	@ModelAttribute("tdusers")
	public List<User> tdusers() {
		return users.findAll();
	}

	// Combo Clientes
	@ModelAttribute("tdclients")
	public List<Client> tdclients() {
		return clients.findAll();
	}

	// Combo Branchs
	@ModelAttribute("tdbranchs")
	public List<Branch> tdbranchs() {
		return branchs.findAll();
	}

	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Sale sale, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;

		}
		try {
			cadastroSaleservice.salvar(sale);
			attributes.addFlashAttribute("mensagem", "Pedido salvo com sucesso!");
			return "redirect:/ithappens/detalhes/" + sale.getCodigo().toString();
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	// Listar Sale
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") SaleFilter filtro) {
		List<Sale> allSale = cadastroSaleservice.filtrar(filtro);
		ModelAndView mv = new ModelAndView(LIST_SALE_VIEW);
		mv.addObject("sales", allSale);
		return mv;
	}

	// PesquisaComboTipos
	@ModelAttribute("todasSales")
	public List<TypeRecipe> todasSales() {
		return Arrays.asList(TypeRecipe.values());
	}

	// PesquisaComboStauts
	@ModelAttribute("todasSalesStatus")
	public List<Status> todasSalesStatus() {
		return Arrays.asList(Status.values());
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Sale sale) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(sale);
		List<User> allUsers = users.findAll();
		mv.addObject("tdusers", allUsers);
		List<Client> allClients = clients.findAll();
		mv.addObject("tdclients", allClients);
		List<Branch> allBranchs = branchs.findAll();
		mv.addObject("tdbranchs", allBranchs);
		return mv;
	}

	// Detalhes Venda
	@RequestMapping("detalhes/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Sale sale) {
		ModelAndView mv = new ModelAndView(DETALHE_SALE_VIEW);
		mv.addObject(sale);
		List<OrderedItem> allOrderedItems = orderedItems.findAll();
		mv.addObject("orderedItems", allOrderedItems);
		List<User> allUsers = users.findAll();
		mv.addObject("tdusers", allUsers);
		List<Status> allStatus = Arrays.asList(Status.values());
		mv.addObject("tdstatus", allStatus);
		return mv;
	}

	// Excluir
	@RequestMapping("delete/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroSaleservice.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Pedido excluída com sucesso!");
		return "redirect:/ithappens/";
	}

}
