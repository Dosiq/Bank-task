package kz.test.task.testbanktask.controller;

import kz.test.task.testbanktask.domain.DTO.TransactionDto;
import kz.test.task.testbanktask.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto) {
        transactionService.processTransaction(transactionDto);
        return ResponseEntity.ok().build();
    }
}
