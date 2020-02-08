package com.restapi.krishna.controller;

import com.restapi.krishna.SpringBootRestApiAndUnitTestingApplication;
import com.restapi.krishna.model.Stock;
import com.restapi.krishna.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockAPI {

    private final StockService stockService;
    private static final Logger log = LoggerFactory.getLogger(SpringBootRestApiAndUnitTestingApplication.class);

    public StockAPI(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> findById(@PathVariable Long stockId) {
        Optional<Stock> stockOptional = stockService.findById(stockId);
        if (!stockOptional.isPresent()) {
            log.error("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stockOptional.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Stock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.save(stock));
    }

    @PatchMapping("/{stockId}")
    public ResponseEntity<Stock> update(@PathVariable Long stockId, @RequestBody Stock updatingStock) {
        Optional<Stock> stockOptional = stockService.findById(stockId);
        if (!stockOptional.isPresent()) {
            log.error("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        Stock stock = stockOptional.get();
        if (!StringUtils.isEmpty(updatingStock.getName())) stock.setName(updatingStock.getName());
        if (!Objects.isNull(updatingStock.getPrice())) stock.setPrice(updatingStock.getPrice());

        return ResponseEntity.accepted().body(stockService.save(stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        stockService.deleteById(id);

        return ResponseEntity.accepted().build();
    }
}
