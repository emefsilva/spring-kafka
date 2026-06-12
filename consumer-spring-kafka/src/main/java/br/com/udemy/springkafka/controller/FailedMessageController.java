package br.com.udemy.springkafka.controller;

import br.com.udemy.springkafka.domain.FailedMessage;
import br.com.udemy.springkafka.services.FailedMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/failed-messages")
public class FailedMessageController {

    private final FailedMessageService failedMessageService;

    public FailedMessageController(FailedMessageService failedMessageService) {
        this.failedMessageService = failedMessageService;
    }

    @GetMapping
    public List<FailedMessage> findAll() {
        return failedMessageService.findAll();
    }
}
