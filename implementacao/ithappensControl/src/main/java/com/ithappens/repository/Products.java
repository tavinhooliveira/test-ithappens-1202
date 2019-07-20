package com.ithappens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Product;

public interface Products extends JpaRepository<Product, Long> {
	
	@Query("SELECT count(codigo) FROM Product")
    public List<Product> findByContProductsQTA();
	
}
