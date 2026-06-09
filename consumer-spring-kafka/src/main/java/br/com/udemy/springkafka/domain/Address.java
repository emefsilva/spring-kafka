package br.com.udemy.springkafka.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String cep;
    private String street;
    private String district;
    private String city;
    private String state;

}
