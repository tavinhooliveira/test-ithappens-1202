package com.ithappens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.User;

public interface Users extends JpaRepository<User, Long> {

	public List<Users> findByNomeContaining(String nome);

	public Optional<Users> findByNomeIgnoreCase(String nome);

	@Query("SELECT count(codigo) FROM User")
	public List<User> findByContUserQTA();

}
