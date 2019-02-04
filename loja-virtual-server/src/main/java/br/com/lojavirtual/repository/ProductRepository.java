package br.com.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojavirtual.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
