
package org.amirensit.pocs.ecomapp.controller;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author achoubani on 04/03/2020.
 */
@Controller
public class SecurityController {

    private AdapterDeploymentContext adapterDeploymentContext;

    public SecurityController(AdapterDeploymentContext adapterDeploymentContext) {
        this.adapterDeploymentContext = adapterDeploymentContext;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return "redirect:/";
    }

    @GetMapping("/changePassword")
    public String changePassword(RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws ServletException {
        HttpFacade httpFacade = new SimpleHttpFacade(httpServletRequest, httpServletResponse);
        KeycloakDeployment deployment = adapterDeploymentContext.resolveDeployment(httpFacade);
        redirectAttributes.addAttribute("referrer", deployment.getResourceName());
        return "redirect:" + deployment.getAccountUrl() + "/password";
    }
}
