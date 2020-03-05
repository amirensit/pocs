package org.amirensit.pocs.ecomapp.controller;

import lombok.Data;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author achoubani on 04/03/2020.
 */
@Controller
public class SupplierController {

    private KeycloakRestTemplate keycloakRestTemplate;

    public SupplierController(KeycloakRestTemplate keycloakRestTemplate) {
        this.keycloakRestTemplate = keycloakRestTemplate;
    }

    @GetMapping("/suppliers")
    public String index(Model model) {
        ResponseEntity<PagedModel<Supplier>> response = keycloakRestTemplate.exchange("http://localhost:8083/suppliers", HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedModel<Supplier>>() {});
        model.addAttribute("suppliers", response.getBody().getContent());
        return "suppliers";
    }

    @ExceptionHandler(Exception.class) // this should be set to the right exception name !
    public String handleException() {
        return "errors";
    }

    @Data
    public static class Supplier {
        private Long id;
        private String name;
        private String email;

    }
}
