package br.com.udemy.springkafka.consumer;

import br.com.udemy.springkafka.avro.PeopleEvent;
import br.com.udemy.springkafka.domain.PeopleEntity;
import br.com.udemy.springkafka.exception.AddressNotFoundException;
import br.com.udemy.springkafka.exception.CustomerNotFoundException;
import br.com.udemy.springkafka.exception.DollarRateException;
import br.com.udemy.springkafka.repository.PeopleEventRepository;
import br.com.udemy.springkafka.services.CustomerEnrichmentService;
import br.com.udemy.springkafka.services.CustomerSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Slf4j
public class PeopleConsumer {


    private final PeopleEventRepository peopleEventRepository;
    private final CustomerEnrichmentService customerEnrichmentService;
    private final CustomerSummaryService customerSummaryService;


    public PeopleConsumer(PeopleEventRepository peopleEventRepository, CustomerEnrichmentService customerEnrichmentService, CustomerSummaryService customerSummaryService) {
        this.peopleEventRepository = peopleEventRepository;
        this.customerEnrichmentService = customerEnrichmentService;
        this.customerSummaryService = customerSummaryService;
    }

    @KafkaListener(topics = "${topic.name}")
    public void receiveMessage(ConsumerRecord<String, br.com.udemy.springkafka.avro.PeopleEvent> record, Acknowledgment ack) {

        var people = record.value();

        log.info("Received message: {} ", people.toString());

        var peopleEntity = getPeopleEntity(record, people);

        peopleEventRepository.save(peopleEntity);

        var customerSummary = customerEnrichmentService.process(people);


        customerSummaryService.save(customerSummary);

        log.info(
                "Customer summary generated for CPF {}",
                people.getCpf()
        );

        ack.acknowledge();

        log.info(
                "Customer summary saved successfully"
        );

    }

    private static PeopleEntity getPeopleEntity(ConsumerRecord<String, PeopleEvent> record, PeopleEvent people) {
        return PeopleEntity.builder()
                .name(people.getName().toString())
                .cpf(people.getCpf().toString())
                .investmentDollar(BigDecimal.valueOf(people.getInvestmentDollar()))
                .cep(people.getCep().toString())
                .topic(record.topic())
                .kafkaOffset(record.offset())
                .receivedAt(LocalDateTime.now())
                .build();
    }
/*
    @KafkaHandler(isDefault = true)
    public void unknown(Object obj, Acknowledgment ack) {
        log.info("Unknown Received: {}", obj.toString());
        ack.acknowledge();
    }

 */
}
