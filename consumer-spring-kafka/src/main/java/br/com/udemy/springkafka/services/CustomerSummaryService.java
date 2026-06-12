package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.CustomerSummary;
import br.com.udemy.springkafka.repository.CustomerSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerSummaryService {

    private final CustomerSummaryRepository customerSummaryRepository;

    public CustomerSummaryService(CustomerSummaryRepository customerSummaryRepository) {
        this.customerSummaryRepository = customerSummaryRepository;
    }

    public void save(CustomerSummary customerSummary) {
        customerSummaryRepository.save(customerSummary);
    }
}
