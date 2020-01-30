package com.example.demo.services;

import com.example.demo.config.validations.OnCreate;
import com.example.demo.config.validations.OnEdit;
import com.example.demo.domain.Market;
import com.example.demo.repositories.MarketRepository;
import com.example.demo.services.dto.MarketDTO;
import com.example.demo.services.mapper.MarketMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/*
* Note that the @Validated annotation must again be applied to the whole class.
* To define which validation group should be active, it must also be applied at method level
 */
@Service
@Validated
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

    @Validated(value = OnCreate.class) // this means that the validation will works only with annotations that has OnCreate as group value
    void validateForCreate(@Valid MarketDTO marketDTO){
        // do something
    }

    @Validated(value = OnEdit.class)
    void validateForUpdate(@Valid MarketDTO marketDTO){
        // do something
    }
}
