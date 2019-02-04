package br.com.lojavirtual.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lojavirtual.model.Form;
import br.com.lojavirtual.model.Product;
import br.com.lojavirtual.model.Sales;
import br.com.lojavirtual.service.ProductService;
import br.com.lojavirtual.service.SalesService;
import br.com.lojavirtual.util.Transaction;

@Controller
public class ViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private SalesService salesService;

    @GetMapping("/home")
    public ModelAndView index() {
    	ModelAndView mv = new ModelAndView("index");
    	mv.addObject("orders", false);
    	
    	try {
    		CompletableFuture<Product> future = productService.findProduct(1L);
    		Product product = future.get();
    		Form form = new Form();
    		form.setId(product.getId());
    		form.setName(product.getName());
    		form.setPrice(product.getPrice());
    		form.setStock(product.getStock());
    		form.setAmount(0);
    		mv.addObject("product", form);
		} catch (Exception e) {
			mv.addObject("erro", "Erro ao recuperar produto!");
			logger.error("index()", e);
		}
    	
		return mv;
    }
    
    @PostMapping("/sendMsg")
    public ModelAndView buyProduct(Form form) {
    	ModelAndView mv = new ModelAndView("index");
    	mv.addObject("orders", false);
    	
    	try {
    		CompletableFuture<Product> future = productService.findProduct(form.getId());
    		Product productDb = future.get();
    		
    		form.setStock(productDb.getStock());
    		
    		Sales sale = new Sales();
    		long idTransaction = Transaction.getInstance().getIdTransaction();
    		sale.setIdTransaction(idTransaction);
    		sale.setDateTransaction(new Date(idTransaction));
    		sale.setAmount(form.getAmount());
    		
    		Product product = new Product();
    		product.setId(form.getId());
    		product.setName(form.getName());
    		product.setPrice(form.getPrice());
    		product.setStock(form.getStock());
    		product.addSales(sale);
    		
    		if(productDb.getStock() < 1) {
    			mv.addObject("erro", "Não temos mais este produto em estoque. Sorry!");
    		} else if ((productDb.getStock() - sale.getAmount()) < 1) {
    			mv.addObject("erro", "Não será possível realizar a compra com a quantidade informada. "
    					           + "Nosso estoque atual é de " + productDb.getStock() + " unidades.");
    		} else {
    			CompletableFuture<HttpStatus> futureBuy = salesService.buyProduct(product);
    			
    			if(futureBuy.get().value() == HttpStatus.OK.value()) {
    				mv.addObject("message", "Seu pedido número " + idTransaction + " foi efetuado com sucesso.");
    			} else {
    				throw new Exception("Erro durante a transação! Tente novamente mais tarde.");
    			}
    		}
    		
    		mv.addObject("product", form);
		} catch (Exception e) {
			mv.addObject("erro", e.getMessage());
			logger.error("buyProduct(Form form)", e);
		}

    	return mv;
    }
    
    @GetMapping("/pedidos")
    public ModelAndView orders() {
    	ModelAndView mv = new ModelAndView("index");
    	mv.addObject("orders", true);
    	
    	List<Sales> sales = salesService.findAllSales();
    	mv.addObject("orderList", sales);
    	
        return mv;
    }
    
    @PostMapping("/searchOrder")
    public ModelAndView serachOrder(Long orderId) {
    	ModelAndView mv = new ModelAndView("index");
    	mv.addObject("orders", true);
    	
    	List<Sales> sales = new ArrayList<>();
    	
    	if(orderId == null) {
    		sales = salesService.findAllSales();
    	} else {
    		Sales sale = salesService.findSaleByIdTransaction(orderId);
    		sales.add(sale);
    	}
    		
    	mv.addObject("orderList", sales);
    	
        return mv;
    }
    
}
