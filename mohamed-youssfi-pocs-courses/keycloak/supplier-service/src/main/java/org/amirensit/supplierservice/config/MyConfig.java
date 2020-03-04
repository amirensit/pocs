package org.amirensit.supplierservice.config;

import org.amirensit.supplierservice.domain.Supplier;
import org.amirensit.supplierservice.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

/**
 * @author achoubani on 04/03/2020.
 */
@Configuration
public class MyConfig {

    @Bean
    CommandLineRunner start(SupplierRepository supplierRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Supplier.class);
            Stream.of("IBM", "HP", "SAMSUNG").forEach(name -> {
                supplierRepository.save(new Supplier(null, name, "contact@" + name + ".com"));
            });
        };
    }

}
