package br.com.lojavirtual.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lojavirtual.model.Product;
import br.com.lojavirtual.model.Sales;

@Service
public class SalesService {

	private static final Logger logger = LoggerFactory.getLogger(SalesService.class);

    private final RestTemplate restTemplate;

    public SalesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("salesAsyncExecutor")
    public CompletableFuture<HttpStatus> buyProduct(Product product) throws InterruptedException {
        logger.info("Looking up " + product);
        ResponseEntity<Void> results = restTemplate.postForEntity("http://localhost:8080/sale/buy", product, Void.class);
        return CompletableFuture.completedFuture(results.getStatusCode());
    }
	
    public Sales findSaleByIdTransaction(Long idTransaction) {
    	ResponseEntity<Sales> results = restTemplate.getForEntity("http://localhost:8080/sale/find/" + idTransaction, Sales.class);
    	return results.getBody();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Sales> findAllSales() {
    	ResponseEntity<List> results = restTemplate.getForEntity("http://localhost:8080/sale/findAll", List.class);
    	return results.getBody();
    }
}
