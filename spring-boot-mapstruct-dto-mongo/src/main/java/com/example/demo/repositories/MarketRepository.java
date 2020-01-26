package com.example.demo.repositories;

import com.example.demo.domain.Market;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketRepository extends MongoRepository<Market, String> {
}
