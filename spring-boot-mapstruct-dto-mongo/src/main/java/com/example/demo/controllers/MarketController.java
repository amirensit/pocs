package com.example.demo.controllers;

import com.example.demo.services.MarketService;
import com.example.demo.services.dto.MarketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/markets")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<MarketDTO> add(@RequestBody MarketDTO marketDTO) {
        MarketDTO result = marketService.add(marketDTO);
        return ResponseEntity.ok().body(result);

    }
}
