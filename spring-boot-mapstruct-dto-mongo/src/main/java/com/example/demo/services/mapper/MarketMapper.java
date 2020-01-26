package com.example.demo.services.mapper;

import com.example.demo.domain.Market;
import com.example.demo.services.dto.MarketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MarketMapper {
    MarketMapper INSTANCE = Mappers.getMapper(MarketMapper.class);

    Market toEntity(MarketDTO marketDTO);

    MarketDTO toDto(Market market);
}
