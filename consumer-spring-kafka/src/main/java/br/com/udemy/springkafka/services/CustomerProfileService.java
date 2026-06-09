package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.CustomerProfile;
import br.com.udemy.springkafka.repository.CustomerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {

    private final CustomerProfileRepository  customerProfileRepository;;

    public CustomerProfileService(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    public CustomerProfile findByCpf(String cpf) {
        return customerProfileRepository
                .findByCpf(cpf)
                .orElseThrow(
                        () -> new RuntimeException("Customer not found: " + cpf));
    }
}
