package br.com.udemy.springkafka.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeopleEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    private String cpf;
    private String cep;
    private BigDecimal investmentDollar;
    private String topic;
    private Long kafkaOffset;
    private LocalDateTime receivedAt;
}
