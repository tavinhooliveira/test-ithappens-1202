package com.ithappens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.OrderedItem;

public interface OrderedItems extends JpaRepository<OrderedItem, Long> {

	@Query("SELECT SUM(totalValue) FROM OrderedItem")
	public List<OrderedItem> findByTotalValueOrderedItem();

}
