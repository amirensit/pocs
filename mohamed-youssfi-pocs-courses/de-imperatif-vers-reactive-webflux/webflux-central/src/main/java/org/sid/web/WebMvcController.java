package org.sid.web;

import org.sid.dao.SocietieRepository;
import org.sid.dao.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebMvcController {

    private final SocietieRepository societieRepository;
    private final TransactionRepository transactionRepository;


    public WebMvcController(SocietieRepository societieRepository, TransactionRepository transactionRepository) {
        this.societieRepository = societieRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("societes", societieRepository.findAll());
        return "index";
    }
}
