package org.amirensit.pocs.ecomapp.controller;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("products");
        return "suppliers";
    }
}
