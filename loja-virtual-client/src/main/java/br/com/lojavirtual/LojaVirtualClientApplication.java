package br.com.lojavirtual;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class LojaVirtualClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LojaVirtualClientApplication.class, args);
    }
    
    @Bean(name = "productAsyncExecutor")
    public Executor taskProductExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ProductThread-");
        executor.initialize();
        return executor;
    }
    
    @Bean(name = "salesAsyncExecutor")
    public Executor taskSalesExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("SalesThread-");
        executor.initialize();
        return executor;
    }
}
