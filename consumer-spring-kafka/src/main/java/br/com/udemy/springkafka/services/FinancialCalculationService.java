package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.Transaction;
import br.com.udemy.springkafka.domain.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialCalculationService {

    public BigDecimal calculateDeposits(List<Transaction> transactions) {

        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.DEPOSIT)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateWithdraws(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.WITHDRAW)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateNetBalance(BigDecimal deposits, BigDecimal withdraws) {
        return deposits.subtract(withdraws);
    }

}
