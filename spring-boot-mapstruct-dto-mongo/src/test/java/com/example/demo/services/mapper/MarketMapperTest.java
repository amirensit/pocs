package com.example.demo.services.mapper;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Market;
import com.example.demo.services.dto.ActorDTO;
import com.example.demo.services.dto.MarketDTO;
import  static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MarketMapperTest {

    private static final String DEFAULT_MARKET_CODE = "m0001";
    private static final String DEFAULT_LABEL = "m0001";
    private static final String DEFAULT_ADDRESS = "m0001";

    MarketMapper marketMapper = MarketMapper.INSTANCE;

    @Test
    public void toDtoTest() throws Exception {

        //given
        Market market = new Market();
        market.setMarketCode(DEFAULT_MARKET_CODE);
        market.setLabel(DEFAULT_LABEL);
        market.setAdress(DEFAULT_ADDRESS);
        Actor actor1 = ActorMapperTest.createActor();
        Actor actor2 = ActorMapperTest.createActor();
        actor2.setFirstName("just a new firstName different from actor1");
        market.addAffectedOperator(actor1);
        market.addAffectedOperator(actor2);

        //when
        MarketDTO marketDTO = marketMapper.toDto(market);
        ActorDTO actorDTOFromAffectedOperatorsDTO = marketDTO.getAffectedOperators().iterator().next();
        //then

        assertThat(marketDTO.getId()).isEqualTo(market.getId());
        assertThat(marketDTO.getAffectedOperators()).size().isEqualTo(2);
        assertThat(actorDTOFromAffectedOperatorsDTO.getFirstName()).isEqualTo("amir");
        //assertThat(marketDTO.getAffectedOperators().contains("error n7eb lehna :p"));



    }



}
