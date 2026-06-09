package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.Transaction;
import br.com.udemy.springkafka.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findByCpf(String cpf) {
        return transactionRepository.findByCpf(cpf);
    }
}
