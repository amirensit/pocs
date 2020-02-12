package fr.ntech.ntechsec.service;

import fr.ntech.ntechsec.repositories.EtudiantRepository;
import fr.ntech.ntechsec.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EtudiantRestService {

    private EtudiantRepository etudiantRepository;

    public EtudiantRestService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_SCOLARITE"})
    @RequestMapping(value = "/saveEtudiant", method = RequestMethod.GET)
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_SCOLARITE", "ROLE_PROF", "ROLE_ETUDIANT"})
    @RequestMapping(value = "/etudiants")
    public Page<Etudiant> listEtudiant(int page, int size) { // we can omit @RequestParam annotation if is a simple type. The method param name will be used by default. See https://reversecoding.net/spring-mvc-requestparam-binding-request-parameters/
        return etudiantRepository.findAll(PageRequest.of(page, size)); // new PageRequest(page, size) is deprecated.
    }

    @RequestMapping(value = "/getLogedUser")
    public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority grantedAuthority: securityContext.getAuthentication().getAuthorities()) {
            roles.add(grantedAuthority.getAuthority());
        }
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("roles", roles);
        return params;
    }
}
