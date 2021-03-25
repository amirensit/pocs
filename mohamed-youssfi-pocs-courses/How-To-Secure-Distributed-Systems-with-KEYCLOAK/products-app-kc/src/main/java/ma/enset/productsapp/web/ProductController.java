package ma.enset.productsapp.web;

import lombok.Data;
import ma.enset.productsapp.repositories.ProductRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        PagedModel<Supplier> pagedModel = keycloakRestTemplate.getForObject("http://localhost:8083/suppliers", PagedModel.class);
        model.addAttribute("suppliers", pagedModel);
        return "suppliers";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> getToken(HttpServletRequest request) {
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) keycloakAuthenticationToken.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
        Map<String, String> map = new HashMap<>();
        map.put("access-token", keycloakSecurityContext.getTokenString());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors";

    }
    @Data
    public static class Supplier {
        private Long id;
        private String name;
        private String email;
    }
}
