package kz.test.task.testbanktask.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class ExternalCurrencyExchangeClient {

    @Autowired
    private WebClient webClient;

    public BigDecimal fetchExchangeRate(String fromCurrency, String toCurrency) {
        String url = "https://api.exchangeratesapi.io/latest?base=" + fromCurrency + "&symbols=" + toCurrency;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> new BigDecimal(jsonNode.get("rates").get(toCurrency).asText()))
                .block();
    }
}
