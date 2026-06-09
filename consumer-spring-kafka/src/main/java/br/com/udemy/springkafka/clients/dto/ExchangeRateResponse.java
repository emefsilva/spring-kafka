package br.com.udemy.springkafka.clients.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateResponse(
        String result,
        String base_code,

        Map<String, BigDecimal> rates

) {
}
