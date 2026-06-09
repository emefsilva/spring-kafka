package br.com.udemy.springkafka.clients.dto;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {
}
