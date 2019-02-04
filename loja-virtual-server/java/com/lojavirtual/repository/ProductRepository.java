package com.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojavirtual.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
