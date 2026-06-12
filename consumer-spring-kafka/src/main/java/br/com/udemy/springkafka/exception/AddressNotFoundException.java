package br.com.udemy.springkafka.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String address) {
        super("Address not found: " + address);
    }
}
