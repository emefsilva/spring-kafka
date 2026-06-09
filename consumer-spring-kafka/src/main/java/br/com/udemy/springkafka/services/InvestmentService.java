package br.com.udemy.springkafka.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InvestmentService {

    public BigDecimal convertDollarToReal(BigDecimal investmentDollar, BigDecimal dollarRate) {
        return investmentDollar.multiply(dollarRate).setScale(2, RoundingMode.HALF_UP);
    }



}
