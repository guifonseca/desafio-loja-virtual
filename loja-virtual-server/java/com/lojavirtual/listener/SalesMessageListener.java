package com.lojavirtual.listener;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.config.RabbitConfig;
import com.lojavirtual.model.Product;
import com.lojavirtual.model.Sales;
import com.lojavirtual.service.ProductService;
import com.lojavirtual.service.SalesService;

@Component
public class SalesMessageListener {

	private static final Logger logger = LoggerFactory.getLogger(SalesMessageListener.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SalesService salesService;

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void processOrder(Product product) {
        logger.info("Product Received from the queue: " + product);
        
        Sales sale = product.getSales().iterator().next();
        Product productDb = productService.findById(product.getId());
        Set<Sales> sales = salesService.findAllSalesByProductId(productDb.getId());
		int stock = productDb.getStock() - sale.getAmount();
		
		if(stock < 0) {
			sale.setError("A compra não pôde ser finalizada devido a falta de estoque.");
		} else {
			sale.setSuccess("Parabéns! Sua compra foi realizada com sucesso e o produto já está sendo encaminhado para a transportadora.");
			product.setStock(stock);
		}
		
		sale.setProduct(product);
		sales.add(sale);
		product.setSales(sales);
		
		logger.info("Product saved: " + productService.save(product));
    }
	
}
