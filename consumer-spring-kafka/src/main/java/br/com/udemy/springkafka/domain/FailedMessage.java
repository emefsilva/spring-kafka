package br.com.udemy.springkafka.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailedMessage {

    @Id
    @GeneratedValue
    private UUID id;
    private String originalTopic;
    private String cpf;
    @Lob
    @Column(length = 10000)
    private String payload;
    private String exceptionType;
    @Column(length = 2000)
    private String exceptionMessage;
    private LocalDateTime createdAt;
}
