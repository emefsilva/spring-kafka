package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.clients.ViaCelClient;
import br.com.udemy.springkafka.domain.Address;
import br.com.udemy.springkafka.exception.AddressNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private final ViaCelClient viaCelClient;

    public AddressService(ViaCelClient viaCelClient) {
        this.viaCelClient = viaCelClient;
    }

    public Address findAddressByCep(String cep) {

        try {
            var response = viaCelClient.findByCep(cep);

            return Address.builder()
                    .cep(cep)
                    .street(response.logradouro())
                    .district(response.bairro())
                    .city(response.localidade())
                    .state(response.uf())
                    .build();

        } catch (Exception e) {
            throw new AddressNotFoundException(e.getMessage() + cep);
        }
    }

}
