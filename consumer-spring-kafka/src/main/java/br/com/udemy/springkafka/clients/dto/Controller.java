package br.com.udemy.springkafka.clients.dto;

import br.com.udemy.springkafka.clients.DollarClient;
import br.com.udemy.springkafka.clients.ViaCelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    private final ViaCelClient viaCelClient;
    private final DollarClient dollarClient;

    public Controller(ViaCelClient viaCelClient, DollarClient dollarClient) {
        this.viaCelClient = viaCelClient;
        this.dollarClient = dollarClient;
    }

    @GetMapping("/test-cep/{cep}")
    public Object findByCep(@PathVariable String cep) {
        return viaCelClient.findByCep(cep);
    }

    @GetMapping("test/dollar")
    public Object findByDollar() {
        return dollarClient.getDollarRate();
    }
}
