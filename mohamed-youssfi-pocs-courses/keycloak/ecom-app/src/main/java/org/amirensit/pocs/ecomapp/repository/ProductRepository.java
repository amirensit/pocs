package org.amirensit.pocs.ecomapp.repository;

import org.amirensit.pocs.ecomapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
