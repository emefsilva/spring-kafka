package br.com.udemy.springkafka.domain.mapper;

import br.com.udemy.springkafka.avro.PeopleEvent;
import br.com.udemy.springkafka.domain.FailedMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FailedMessageMapper {

    public FailedMessage toEntity(
            ConsumerRecord<String, PeopleEvent> record) {

        var event = record.value();

        return FailedMessage.builder()
                .originalTopic(
                        getHeader(record,
                                "kafka_dlt-original-topic"))
                .cpf(event.getCpf().toString())
                .payload(event.toString())
                .exceptionType(getHeader(record,
                        "kafka_dlt-exception-fqcn"))
                .exceptionMessage(getHeader(record,
                        "kafka_dlt-exception-message"))
                .createdAt(LocalDateTime.now())
                .build();
    }

    private String getHeader(
            ConsumerRecord<String, PeopleEvent> record,
            String headerName) {

        var header = record.headers().lastHeader(headerName);

        return header != null
                ? new String(header.value())
                : null;
    }
}
