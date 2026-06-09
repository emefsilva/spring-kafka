package br.com.udemy.springkafka.clients;

import br.com.udemy.springkafka.clients.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient
        (name = "viacep",
        url = "https://viacep.com.br/ws")
public interface ViaCelClient {

    @GetMapping("/{cep}/json")
    ViaCepResponse findByCep(@PathVariable String cep);
}
