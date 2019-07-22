package com.ithappens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Branch;

public interface Branchs extends JpaRepository<Branch, Long> {

	public List<Branchs> findByNomeContaining(String nome);

	public Optional<Branchs> findByNomeIgnoreCase(String nome);

	@Query("SELECT count(codigo) FROM Branch")
	public List<Branch> findByContBranchsQTA();

}
