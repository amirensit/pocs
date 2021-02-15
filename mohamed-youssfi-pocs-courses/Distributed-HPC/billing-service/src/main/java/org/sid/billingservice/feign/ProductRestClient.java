package org.sid.billingservice.feign;

import org.sid.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductRestClient {

    @GetMapping(path = "/products")
    public PagedModel<Product> findPagedProducts(Pageable pageable);

    @GetMapping(path = "/products/{id}")
    public Product findProductById(@PathVariable("id") Long id);
}
