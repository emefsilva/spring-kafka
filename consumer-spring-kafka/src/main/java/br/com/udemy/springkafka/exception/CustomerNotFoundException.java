package br.com.udemy.springkafka.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String cpf) {
        super("Customer not found: " + cpf);
    }
}
