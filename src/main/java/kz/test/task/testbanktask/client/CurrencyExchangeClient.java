package kz.test.task.testbanktask.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class CurrencyExchangeClient {

    private final WebClient webClient;
    private final String apiKey = "YOUR_API_KEY";
    private final String baseUrl = "https://openexchangerates.org/api/";

    public CurrencyExchangeClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public BigDecimal fetchExchangeRate(String fromCurrency, String toCurrency) {
        String url = baseUrl + "latest.json?app_id=" + apiKey + "&symbols=" + toCurrency + "&base=" + fromCurrency;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> new BigDecimal(jsonNode.path("rates").path(toCurrency).asText()))
                .block();
    }
}

