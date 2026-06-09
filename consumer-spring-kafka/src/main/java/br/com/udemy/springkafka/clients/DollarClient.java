package br.com.udemy.springkafka.clients;

import br.com.udemy.springkafka.clients.dto.ExchangeRateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class DollarClient {

    private final ObjectMapper objectMapper;


    public DollarClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BigDecimal getDollarRate() {

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://open.er-api.com/v6/latest/USD")
                    ).GET().build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ExchangeRateResponse exchangeRate = objectMapper.readValue(response.body(), ExchangeRateResponse.class);

            return exchangeRate.rates().get("BRL");

        } catch (Exception e) {
            throw new RuntimeException("Error calling  Dollar API", e);
        }

    }
}
