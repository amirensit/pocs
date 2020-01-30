package com.example.demo.services;

import com.example.demo.services.dto.MarketDTO;
import com.example.demo.services.mapper.MarketMapperTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    public void whenLabelMarketDTOIsInvalidForCreate_thenThrowsException() {
        MarketDTO marketDTO = MarketMapperTest.createMarketDTO();
        marketDTO.setLabel(null);
        assertThrows(ConstraintViolationException.class, () -> {
            marketService.validateForCreate(marketDTO);
        });
    }

    @Test
    public void whenLabelMarketDTOIsInvalidForUpdate_thenDoNotThrowsException() {
        MarketDTO marketDTO = MarketMapperTest.createMarketDTO();
        marketDTO.setLabel(null);
        // here we are calling the validateForUpdate(marketDTO). If the test pass, that means there is no exception thrown.
        marketService.validateForUpdate(marketDTO);
    }

}
