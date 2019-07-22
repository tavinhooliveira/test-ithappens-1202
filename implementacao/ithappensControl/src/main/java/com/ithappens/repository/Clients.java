package com.ithappens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Client;

public interface Clients extends JpaRepository<Client, Long> {

	public List<Clients> findByNomeContaining(String nome);

	public Optional<Clients> findByNomeIgnoreCase(String nome);

	@Query("SELECT count(codigo) FROM Client")
	public List<Client> findByContClientsQTA();

}
