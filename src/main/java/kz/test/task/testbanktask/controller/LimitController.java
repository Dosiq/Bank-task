package kz.test.task.testbanktask.controller;

import kz.test.task.testbanktask.domain.DTO.LimitDto;
import kz.test.task.testbanktask.domain.DTO.TransactionDto;
import kz.test.task.testbanktask.domain.model.Limit;
import kz.test.task.testbanktask.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limits")
public class LimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping
    public ResponseEntity<Void> setLimit(@RequestBody LimitDto limitDto) {
        limitService.setNewLimit(limitDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exceeded")
    public ResponseEntity<List<TransactionDto>> getExceededTransactions() {
        List<TransactionDto> transactions = limitService.findExceededTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Limit>> getAllLimits() {
        List<Limit> limits = limitService.getAllLimits();
        return ResponseEntity.ok(limits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLimit(@PathVariable Long id, @RequestBody LimitDto limitDto) {
        limitService.updateLimit(id, limitDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimit(@PathVariable Long id) {
        limitService.deleteLimit(id);
        return ResponseEntity.ok().build();
    }
}
