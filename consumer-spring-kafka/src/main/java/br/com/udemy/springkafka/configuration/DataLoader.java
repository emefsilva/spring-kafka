package br.com.udemy.springkafka.configuration;

import br.com.udemy.springkafka.domain.CustomerProfile;
import br.com.udemy.springkafka.domain.Transaction;
import br.com.udemy.springkafka.repository.CustomerProfileRepository;
import br.com.udemy.springkafka.repository.TransactionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

@Configuration
@Slf4j
public class DataLoader {

    private final TransactionRepository transactionRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(TransactionRepository transactionRepository, CustomerProfileRepository customerProfileRepository, ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.objectMapper = objectMapper;
    }


    @Bean
    CommandLineRunner loadTransactions() {

        return args -> {

            if (!transactionRepository.findAll().isEmpty()) {
                return;
            }

            InputStream inputStream = new ClassPathResource("data/transactions.json").getInputStream();

            List<Transaction> transactions = objectMapper.readValue(inputStream, new TypeReference<>() {
            });

            transactionRepository.saveAll(transactions);

            log.info("Transactions saved");

        };
    }

    @Bean
    CommandLineRunner loadCustomerProfiles() {

        return args -> {
            if (!customerProfileRepository.findAll().isEmpty()) {
                return ;
            }

            InputStream inputStream = new ClassPathResource("data/customerProfile.json").getInputStream();

            List<CustomerProfile> customerProfiles = objectMapper.readValue(inputStream, new TypeReference<>() {});

            customerProfileRepository.saveAll(customerProfiles);

            log.info("CustomerProfiles saved");

        };
    }
}
