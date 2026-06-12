package br.com.udemy.springkafka.configuration;

import br.com.udemy.springkafka.exception.AddressNotFoundException;
import br.com.udemy.springkafka.exception.CustomerNotFoundException;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaTopicConfiguration {

    private final String topicName;
    private final String dltName;
    private final Long interval;
    private final Long maxAttempts;

    public KafkaTopicConfiguration(
            @Value("${topic.name}") String topicName,
            @Value("${topic.dltName}") String dltName,
            @Value("${topic.interval}") Long interval,
            @Value("${topic.maxAttempts}") Long maxAttempts) {
        this.topicName = topicName;
        this.dltName = dltName;
        this.interval = interval;
        this.maxAttempts = maxAttempts;
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(
                topicName, 1, (short) 1);
    }

    @Bean
    public NewTopic peopleDltTopic() {
        return new NewTopic(
                dltName, 1, (short) 1);
    }

    @Bean
    public DefaultErrorHandler errorHandler(
            KafkaTemplate<Object, Object> kafkaTemplate) {

        var recoverer =
                new DeadLetterPublishingRecoverer(kafkaTemplate, ((
                  consumerRecord, ex) ->
                new TopicPartition(
                        dltName,
                        consumerRecord.partition())
            )
        );

        var backOff =
                new FixedBackOff(
                        interval,
                        maxAttempts
                );

        var errorHandler =
                new DefaultErrorHandler(
                        recoverer,
                        backOff
                );

        errorHandler.addNotRetryableExceptions(
                CustomerNotFoundException.class,
                AddressNotFoundException.class
        );

        return errorHandler;
    }
}
