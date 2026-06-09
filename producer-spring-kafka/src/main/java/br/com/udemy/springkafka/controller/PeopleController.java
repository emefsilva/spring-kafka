package br.com.udemy.springkafka.controller;

import br.com.udemy.springkafka.avro.PeopleEvent;
import br.com.udemy.springkafka.domain.dto.PeopleDTO;
import br.com.udemy.springkafka.producer.PeopleProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/people")
@Tag(
        name = "People",
        description = "Relation operations to publish event kafka"
)
public class PeopleController {

    private final PeopleProducer peopleProducer;


    public PeopleController(PeopleProducer peopleProducer) {
        this.peopleProducer = peopleProducer;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Send client for kafka", description = "Received data from clients and publish kafka event")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Success"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            )
    })
    public ResponseEntity<PeopleEvent> sendMessage(@RequestBody PeopleDTO peopleDTO) {

        var id = UUID.randomUUID().toString();

        var message = PeopleEvent.newBuilder()
                .setId(id).setName(peopleDTO.name())
                .setCpf(peopleDTO.cpf()).setCep(peopleDTO.cep())
                .setInvestmentDollar(peopleDTO.investmentDollar().doubleValue())
                .build();

        peopleProducer.sendMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
