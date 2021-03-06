package br.com.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavirtual.model.Product;
import br.com.lojavirtual.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findOne(@PathVariable("id") Long id) {
		Product product = productService.findById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}
