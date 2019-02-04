package br.com.lojavirtual.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lojavirtual.model.Product;

@Service
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final RestTemplate restTemplate;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("productAsyncExecutor")
    public CompletableFuture<Product> findProduct(Long id) throws InterruptedException {
        logger.info("Looking up " + id);
        Product results = restTemplate.getForObject("http://localhost:8080/product/" + id, Product.class);
        return CompletableFuture.completedFuture(results);
    }

}
