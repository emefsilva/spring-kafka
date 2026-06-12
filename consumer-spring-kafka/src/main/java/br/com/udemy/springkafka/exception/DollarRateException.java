package br.com.udemy.springkafka.exception;

public class DollarRateException extends RuntimeException {

    public DollarRateException(String dollarApi) {
        super("unavailable API: " + dollarApi);
    }
}
