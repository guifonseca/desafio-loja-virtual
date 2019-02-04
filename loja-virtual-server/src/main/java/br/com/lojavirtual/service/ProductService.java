package br.com.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojavirtual.model.Product;
import br.com.lojavirtual.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product findById(long id) {
		return productRepository.findById(id).get();
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}
	
}
