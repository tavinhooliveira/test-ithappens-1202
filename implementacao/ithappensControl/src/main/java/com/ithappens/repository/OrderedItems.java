package com.ithappens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ithappens.model.OrderedItem;

public interface OrderedItems extends JpaRepository<OrderedItem,Long> {
	

}
