package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.AccountCategory;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountCategoryService {

    public AccountCategory classify(BigDecimal netBalance
            , BigDecimal investmentBRL) {

        BigDecimal total = netBalance.add(investmentBRL);

        if (total.compareTo(
                BigDecimal.valueOf(10_000)) <= 0) {
            return AccountCategory.BRONZE;
        }

        if (total.compareTo(
                BigDecimal.valueOf(50_000)) <= 0) {
            return AccountCategory.SILVER;
        }

        if (total.compareTo(
                BigDecimal.valueOf(100_000)) <= 0) {
            return AccountCategory.GOLD;
        }

        return AccountCategory.PLATINUM;

    }
}
