package kz.test.task.testbanktask.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;

@Component
public class CurrencyExchangeClient {

    private final RestTemplate restTemplate;
    private final String apiKey = "YOUR_API_KEY";
    private final String baseUrl = "https://openexchangerates.org/api/";

    public CurrencyExchangeClient() {
        this.restTemplate = new RestTemplate();
    }

    public BigDecimal fetchExchangeRate(String fromCurrency, String toCurrency) {
        String url = baseUrl + "latest.json?app_id=" + apiKey + "&symbols=" + toCurrency + "&base=" + fromCurrency;
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        return new BigDecimal(jsonNode.path("rates").path(toCurrency).asText());
    }
}
