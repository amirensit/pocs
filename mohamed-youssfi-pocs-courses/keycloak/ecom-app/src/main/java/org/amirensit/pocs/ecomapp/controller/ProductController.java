package org.amirensit.pocs.ecomapp.controller;

import org.amirensit.pocs.ecomapp.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";

    }

    @GetMapping("/products")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";

    }
}
