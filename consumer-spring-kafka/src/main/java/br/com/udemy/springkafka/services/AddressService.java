package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.clients.ViaCelClient;
import br.com.udemy.springkafka.clients.dto.ViaCepResponse;
import br.com.udemy.springkafka.domain.Address;
import br.com.udemy.springkafka.domain.CustomerProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {


    private final ViaCelClient viaCelClient;

    public AddressService(ViaCelClient viaCelClient) {
        this.viaCelClient = viaCelClient;
    }

    public Address findAddressByCep(String cep) {

        var response = viaCelClient.findByCep(cep);

        return Address.builder()
                .cep(cep)
                .street(response.logradouro())
                .district(response.bairro())
                .city(response.localidade())
                .state(response.uf())
                .build();
    }

}
