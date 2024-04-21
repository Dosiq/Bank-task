package kz.test.task.testbanktask.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class ExternalCurrencyExchangeClient {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalCurrencyExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal fetchExchangeRate(String fromCurrency, String toCurrency) {
        String url = "https://api.exchangeratesapi.io/latest?base=" + fromCurrency + "&symbols=" + toCurrency;
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        return new BigDecimal(jsonNode.get("rates").get(toCurrency).asText());
    }
}
