package kz.test.task.testbanktask.service;

import kz.test.task.testbanktask.domain.model.CurrencyExchange;
import kz.test.task.testbanktask.repository.CurrencyExchangeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CurrencyExchangeServiceTests {

    @Mock
    private CurrencyExchangeRepository currencyExchangeRepository;

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @Test
    public void getExchangeRate_ShouldReturnCachedRate_WhenAvailable() {
        String currency = "USD";
        BigDecimal expectedRate = new BigDecimal("1.25");
        LocalDate today = LocalDate.now();

        CurrencyExchange exchange = new CurrencyExchange();
        exchange.setCurrency(currency);
        exchange.setRate(expectedRate);
        exchange.setDate(today);

        when(currencyExchangeRepository.findByCurrencyAndDate(currency, today))
                .thenReturn(Optional.of(exchange));

        BigDecimal actualRate = currencyExchangeService.getExchangeRate(currency);

        assertEquals(expectedRate, actualRate);
    }

}
