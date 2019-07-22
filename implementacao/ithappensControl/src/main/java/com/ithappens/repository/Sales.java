package com.ithappens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Sale;

public interface Sales extends JpaRepository<Sale, Long> {

	public List<Sale> findByDescricaoContaining(String descricao);

	@Query("SELECT count(codigo) FROM Sale")
	public List<Sale> findBySaleTotalQTA();

	@Query("SELECT count(codigo) FROM Sale where status = 'ATIVO'")
	public List<Sale> findBySaleAtivoQTA();

	@Query("SELECT count(codigo) FROM Sale where status = 'CANCELADO'")
	public List<Sale> findBySaleCanceladoQTA();

	@Query("SELECT count(codigo) FROM Sale where status = 'PROCESSADO'")
	public List<Sale> findBySaleProcessandoQTA();

}
