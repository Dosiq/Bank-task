package kz.test.task.testbanktask.service;

import kz.test.task.testbanktask.domain.DTO.TransactionDto;
import kz.test.task.testbanktask.domain.model.Limit;
import kz.test.task.testbanktask.domain.model.Transaction;
import kz.test.task.testbanktask.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private LimitService limitService;

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void processTransaction_ShouldSetLimitExceeded_WhenAmountExceedsLimit() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setSum(new BigDecimal("1200"));
        transactionDto.setCurrencyShortName("USD");
        transactionDto.setDatetime("2022-01-02T12:00:00");
        when(currencyExchangeService.getExchangeRate("USD")).thenReturn(new BigDecimal("1"));
        when(limitService.getCurrentLimit()).thenReturn(new Limit(new BigDecimal("1000"), LocalDateTime.now(), "USD"));
        transactionService.processTransaction(transactionDto);
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(transactionCaptor.capture());
        assertTrue(transactionCaptor.getValue().getLimitExceeded());
    }
}

