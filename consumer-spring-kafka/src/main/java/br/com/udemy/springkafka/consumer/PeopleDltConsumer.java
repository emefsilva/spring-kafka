package br.com.udemy.springkafka.consumer;

import br.com.udemy.springkafka.avro.PeopleEvent;
import br.com.udemy.springkafka.domain.mapper.FailedMessageMapper;
import br.com.udemy.springkafka.repository.FailedMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PeopleDltConsumer {

    private final FailedMessageRepository failedMessageRepository;
    private final FailedMessageMapper failedMessageMapper;

    public PeopleDltConsumer(FailedMessageRepository failedMessageRepository, FailedMessageMapper failedMessageMapper) {
        this.failedMessageRepository = failedMessageRepository;
        this.failedMessageMapper = failedMessageMapper;
    }

    @KafkaListener(topics = "${topic.dltName}", groupId = "people-dlt-group")
    public void receiveDlt(
            ConsumerRecord<String, PeopleEvent> record,
            Acknowledgment ack) {

        log.error(
                "Message received in DLT. CPF: {}", record.value().getCpf());

        failedMessageRepository.save(failedMessageMapper.toEntity(record));

        ack.acknowledge();

        log.info("Failed message saved successfully. CPF: {}`", record.value().getCpf());
    }
}
