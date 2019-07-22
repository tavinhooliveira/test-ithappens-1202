package com.ithappens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ithappens.model.Sale;
import com.ithappens.repository.Sales;
import com.ithappens.repository.filter.SaleFilter;

@Service
public class CadastroSaleService {

	@Autowired
	private Sales sales;

	// Metodo Salvar
	public void salvar(Sale sale) {
		try {
			sales.save(sale);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}

	}

	// Metodo Listar
	public List<Sale> filtrar(SaleFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return sales.findByDescricaoContaining(descricao);
	}

	// Metodo Excluir
	public void excluir(Long codigo) {
		sales.delete(codigo);
	}

}
