package kz.test.task.testbanktask.service;

import kz.test.task.testbanktask.domain.model.CurrencyExchange;
import kz.test.task.testbanktask.repository.CurrencyExchangeRepository;
import kz.test.task.testbanktask.util.ExternalCurrencyExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private ExternalCurrencyExchangeClient exchangeClient;

    public BigDecimal getExchangeRate(String currency) {
        LocalDate today = LocalDate.now();
        return currencyExchangeRepository.findByCurrencyAndDate(currency, today)
                .map(CurrencyExchange::getRate)
                .orElseGet(() -> fetchAndSaveExchangeRate(currency));
    }

    private BigDecimal fetchAndSaveExchangeRate(String currency) {
        BigDecimal rate = exchangeClient.fetchExchangeRate(currency, "USD");
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setCurrency(currency);
        currencyExchange.setRate(rate);
        currencyExchange.setDate(LocalDate.now());
        currencyExchangeRepository.save(currencyExchange);
        return rate;
    }

    public BigDecimal convertAmount(BigDecimal amount, String fromCurrency, String toCurrency) {
        BigDecimal rate = fetchExchangeRate(fromCurrency, toCurrency);
        return amount.multiply(rate);
    }

    public BigDecimal fetchExchangeRate(String fromCurrency, String toCurrency) {
        LocalDate today = LocalDate.now();
        return currencyExchangeRepository.findByCurrencyAndDate(fromCurrency, today)
                .map(CurrencyExchange::getRate)
                .orElseGet(() -> {
                    BigDecimal rate = exchangeClient.fetchExchangeRate(fromCurrency, toCurrency);
                    saveExchangeRate(fromCurrency, rate, today);
                    return rate;
                });
    }

    private void saveExchangeRate(String currency, BigDecimal rate, LocalDate date) {
        CurrencyExchange exchange = new CurrencyExchange();
        exchange.setCurrency(currency);
        exchange.setRate(rate);
        exchange.setDate(date);
        currencyExchangeRepository.save(exchange);
    }

}
