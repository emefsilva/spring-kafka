package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.domain.FailedMessage;
import br.com.udemy.springkafka.repository.FailedMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FailedMessageService {

    private final FailedMessageRepository failedMessageRepository;

    public FailedMessageService(FailedMessageRepository failedMessageRepository) {
        this.failedMessageRepository = failedMessageRepository;
    }

    public FailedMessage save(FailedMessage failedMessage) {
        return failedMessageRepository.save(failedMessage);
    }

    public List<FailedMessage> findAll() {
        return failedMessageRepository.findAll();
    }
}
