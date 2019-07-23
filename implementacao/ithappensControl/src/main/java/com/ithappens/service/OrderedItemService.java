package com.ithappens.service;

import java.math.BigDecimal;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.ithappens.model.OrderedItem;
import com.ithappens.model.Status;
import com.ithappens.repository.OrderedItems;
import com.ithappens.service.exception.ProductsStoqueException;

@Service
public class OrderedItemService {

	@Autowired
	private OrderedItems orderedItems;

	// Incrementar Pedidos a venda;
	public void orderProductSave(OrderedItem orderedItem) throws ProductsStoqueException {
		verifyStok(orderedItem.getQtdProduct(), orderedItem.getProducts().getQtdProduct());
		orderedItem.setStatus(Status.PROCESSADO);
		orderedItem.setTotalValue(calculateCost(orderedItem.getQtdProduct(), orderedItem.getProducts().getValue()));
		orderedItem.getProducts().setQtdProduct(
				decrementOfstock(orderedItem.getProducts().getQtdProduct(), orderedItem.getQtdProduct()));
		orderedItem.getSales()
				.setTotal(updateTotalValueOfSale(orderedItem.getSales().getTotal(), orderedItem.getTotalValue()));
		orderedItem.getSales().setQtdProduct(
				updateQuantityItemSale(orderedItem.getSales().getQtdProduct(), orderedItem.getQtdProduct()));
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
	public BigDecimal updateTotalValueOfSale(BigDecimal totalValueSale, BigDecimal totalValueOrdered) {
		return totalValueSale.add(totalValueOrdered);
	}

	// Atualizar a quantidade da venda total
	public int updateQuantityItemSale(int totalQuantitySale, int totalQuantityOrdered) {
		int totalProduct = totalQuantitySale + totalQuantityOrdered;
		return totalProduct;
	}

}
