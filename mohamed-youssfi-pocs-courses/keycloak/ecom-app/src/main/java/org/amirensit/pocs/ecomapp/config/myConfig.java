package org.amirensit.pocs.ecomapp.config;

import org.amirensit.pocs.ecomapp.domain.Product;
import org.amirensit.pocs.ecomapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class myConfig {

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "Ord HP 564", 3000D));
            productRepository.save(new Product(null, "Ord Asus 398", 38500D));
            productRepository.save(new Product(null, "Ord lenavo 128", 4180D));
            productRepository.findAll().stream().forEach(
                    product -> product.getName()
            );
        };
    }
}
