package com.ithappens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ithappens.repository.Branchs;
import com.ithappens.repository.Clients;
import com.ithappens.repository.Products;
import com.ithappens.repository.Sales;
import com.ithappens.repository.Users;


@Controller
@RequestMapping("/ithappens/dashboard")
public class DashboardController {	
	
	private static final String DASHBOARD = "/layout/Dashboard"; 
	
	@Autowired
	private Sales sales;
	
	@Autowired
	private Clients clients;
	
	@Autowired
	private Users users;
	
	@Autowired
	private Branchs branchs;
	
	@Autowired
	private Products products;
	
	
	//Dashboard
	@RequestMapping
	public ModelAndView exibir() {
		ModelAndView mv = new ModelAndView(DASHBOARD);
		mv.addObject("saleContTotal", sales.findBySaleTotalQTA());
		mv.addObject("saleContAtivo", sales.findBySaleAtivoQTA());
		mv.addObject("saleContProcessando", sales.findBySaleProcessandoQTA());
		mv.addObject("saleContCancelado", sales.findBySaleCanceladoQTA());
		
		mv.addObject("ContClients", clients.findByContClientsQTA());
		mv.addObject("ContUser", users.findByContUserQTA());
		
		mv.addObject("ContBranchs", branchs.findByContBranchsQTA());
		mv.addObject("ContProducts", products.findByContProductsQTA());
		return mv;
	}
	
		
		
			


}
