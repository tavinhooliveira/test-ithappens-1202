package com.ithappens.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ithappens.model.OrderedItem;
import com.ithappens.model.Status;
import com.ithappens.repository.OrderedItems;
import com.ithappens.service.exception.ProductsStoqueException;

@Service
public class OrderedItemService {

	@Autowired
	private OrderedItems orderedItems;

	// Processar pedidos
	public void orderProductSave(OrderedItem orderedItem) throws ProductsStoqueException {
		verifyStok(orderedItem.getQtdProduct(), orderedItem.getProducts().getQtdProduct());
		orderedItem.setStatus(Status.PROCESSADO);
		orderedItem.setTotalValue(calculateCost(orderedItem.getQtdProduct(), orderedItem.getProducts().getValue()));
		orderedItem.getProducts().setQtdProduct(
				decrementOfstock(orderedItem.getProducts().getQtdProduct(), orderedItem.getQtdProduct()));
		orderedItems.save(orderedItem);
	}

	// Calcular o valor sobre a quantidade
	public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
		BigDecimal itemCost = BigDecimal.ZERO;
		BigDecimal totalCost = BigDecimal.ZERO;
		itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
		totalCost = totalCost.add(itemCost);
		return totalCost;
	}

	// Verificar quantidade disponível em estoque
	public void verifyStok(int qtdItemOrdem, int qtdPtoductStok) throws ProductsStoqueException {
		if (qtdItemOrdem > qtdPtoductStok) {
			throw new ProductsStoqueException(
					"A quantidade de produto solicitado é maior que a quantidade do estoque.");
		}
	}

	// Decrementar do estoque
	public int decrementOfstock(int qtdProductEstoque, int qtdProductOrdered) {
		int totalProduct = (qtdProductEstoque - qtdProductOrdered);
		return totalProduct;
	}

	// Atualizar o valor total da venda
	public void updateTotalValueOfSale() {

	}

}
