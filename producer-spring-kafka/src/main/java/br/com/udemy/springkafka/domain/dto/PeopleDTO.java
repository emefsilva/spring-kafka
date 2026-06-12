package br.com.udemy.springkafka.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PeopleDTO(

        @Schema(
                description = "Full name", example = "Emerson Ferreira"
        )
        String name,
        @Schema(description = "CPF client", example = "52423625125")
        String cpf,
        @Schema(description = "principal CEP client", example = "03295000")
        String cep,
        @Schema(description = "Value dollar investment", example = "15000.50")
        BigDecimal investmentDollar) {
}
