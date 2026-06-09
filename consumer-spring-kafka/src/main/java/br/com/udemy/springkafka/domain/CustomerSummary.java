package br.com.udemy.springkafka.domain;

import jakarta.persistence.*;
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
public class CustomerSummary {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    private String cpf;
    private String agency;
    private String account;
    @Enumerated(EnumType.STRING)
    private AccountType typeAccount;
    private String segment;
    @Embedded
    private Address address;
    private BigDecimal investmentDollar;
    private BigDecimal dollarRate;
    private BigDecimal investmentBRL;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdraws;
    private BigDecimal netBalance;

    @Enumerated(EnumType.STRING)
    private AccountCategory accountCategory;
    private LocalDateTime processedAt;


}
