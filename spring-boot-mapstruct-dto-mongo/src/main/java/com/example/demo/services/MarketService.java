package com.example.demo.services;

import com.example.demo.domain.Market;
import com.example.demo.repositories.MarketRepository;
import com.example.demo.services.dto.MarketDTO;
import com.example.demo.services.mapper.MarketMapper;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    private final MarketMapper marketMapper;
    private final MarketRepository marketRepository;

    public MarketService(MarketMapper marketMapper, MarketRepository marketRepository) {
        this.marketMapper = marketMapper;
        this.marketRepository = marketRepository;
    }

    public MarketDTO add(MarketDTO marketDTO) {
        Market market = marketMapper.toEntity(marketDTO);
        market = marketRepository.save(market);
        return marketMapper.toDto(market);
    }
}
