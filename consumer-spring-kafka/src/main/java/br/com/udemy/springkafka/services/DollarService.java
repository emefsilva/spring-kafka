package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.clients.DollarClient;
import br.com.udemy.springkafka.clients.ViaCelClient;
import br.com.udemy.springkafka.exception.DollarRateException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DollarService {

    private final DollarClient dollarClient;

    public DollarService(DollarClient dollarClient) {
        this.dollarClient = dollarClient;
    }

    BigDecimal getCurrentDollarRate() {

        try {

            return dollarClient.getDollarRate();
        } catch (Exception e) {
            throw  new DollarRateException(e.getMessage());
        }
    }
}
