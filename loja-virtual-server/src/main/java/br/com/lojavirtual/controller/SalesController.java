package br.com.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojavirtual.model.Product;
import br.com.lojavirtual.model.Sales;
import br.com.lojavirtual.service.SalesService;

@RestController
public class SalesController {
	
	@Autowired
    private SalesService salesService;
	
	@PostMapping("/sale/buy")
	public ResponseEntity<Void> buyProduct(@RequestBody Product product) {
		salesService.sendSale(product);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/sale/find/{idTransaction}")
	public ResponseEntity<Sales> findSaleByIdTransaction(@PathVariable("idTransaction") Long idTransaction) {
		Sales sale = salesService.findSaleByIdTransaction(idTransaction);
		return new ResponseEntity<Sales>(sale, HttpStatus.OK);
	}
	
	@GetMapping("/sale/findAll")
	public ResponseEntity<List<Sales>> findAll() {
		List<Sales> sales = salesService.findAll();
		return new ResponseEntity<List<Sales>>(sales, HttpStatus.OK);
	}
}
