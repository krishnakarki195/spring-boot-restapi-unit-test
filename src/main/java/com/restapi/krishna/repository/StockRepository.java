package com.restapi.krishna.repository;

import com.restapi.krishna.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}