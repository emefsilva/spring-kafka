package br.com.udemy.springkafka.repository;

import br.com.udemy.springkafka.domain.FailedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FailedMessageRepository extends JpaRepository<FailedMessage, UUID> {
}
