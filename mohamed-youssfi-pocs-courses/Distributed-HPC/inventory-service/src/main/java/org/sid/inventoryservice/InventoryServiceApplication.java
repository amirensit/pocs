package org.sid.inventoryservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ProductRepository productRepository,
            RepositoryRestConfiguration repositoryRestConfiguration
    ) {
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null, "product 1", 12.3, 15.6));
            productRepository.save(new Product(null, "product 2", 11.9, 16.6));
            productRepository.save(new Product(null, "product 3", 10.3658, 17.6));
        };
    }
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Double quantite;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long> {
}
