
package br.com.udemy.springkafka.producer;

import br.com.udemy.springkafka.avro.PeopleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PeopleProducer {

    private final String topicName;
    private final KafkaTemplate<String, PeopleEvent> kafkaTemplate;

    public PeopleProducer(@Value("${topic.name}") String topicName,
                          KafkaTemplate<String, PeopleEvent> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PeopleEvent people) {
        kafkaTemplate.send(topicName, people).whenComplete(
                (success, failure) -> {
                    if (failure == null) {
                        log.info("Message sent successfully");
                    } else {
                        log.info("Message sent failed");

                    }
                });
    }
}
