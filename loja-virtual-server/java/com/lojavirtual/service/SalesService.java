package com.lojavirtual.service;

import java.util.List;
import java.util.Set;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.config.RabbitConfig;
import com.lojavirtual.model.Product;
import com.lojavirtual.model.Sales;
import com.lojavirtual.repository.SalesRepository;

@Service
public class SalesService {
	
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	private SalesRepository salesRepository;
	
	@Autowired
    public SalesService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	public void sendSale(Product product) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, product);
    }
	
	public Sales findSaleByIdTransaction(Long idTransaction) {
		return salesRepository.findSaleByIdTransaction(idTransaction);
	}
	
	public List<Sales> findAll() {
		return salesRepository.findAll();
	}

	public Set<Sales> findAllSalesByProductId(long id) {
		return salesRepository.findAllSalesByProductId(id);
	}
}
