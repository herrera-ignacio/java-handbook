package com.learnkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.entity.LibraryEvent;
import com.learnkafka.jpa.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private LibraryEventsRepository libraryEventsRepository;

    public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);

        log.info("LibraryEvent : {}", libraryEvent);

        // Retry specific exception demo
        if (libraryEvent.getLibraryEventId() != null && libraryEvent.getLibraryEventId() == 000) {
            throw new RecoverableDataAccessException("Retry Specific Exception Demo");
        }

        switch(libraryEvent.getLibraryEventType()) {
            case NEW:
                save(libraryEvent);
                break;
            case UPDATE:
                validate(libraryEvent);
                save(libraryEvent);
                break;
            default:
                log.info("Invalid Library Event Type");
        }
    }

    public void handleRecovery(ConsumerRecord<Integer, String> record) {
        Integer key = record.key();

        String message = record.value();

        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.sendDefault(key, message);

        // Async behavior
        addSendCallback(key, message, listenableFuture);
    }

    private void addSendCallback(Integer key, String value, ListenableFuture<SendResult<Integer, String>> listenableFuture) {
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            // Handles publish status
            @Override
            public void onFailure(@NotNull Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error sending the message with key {} and value {} and the exception is {}", key, value, ex.getMessage());

        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message sent successfully for the key: {} and the value is {}, partition is {}", key, value, result.getRecordMetadata().partition());
    }

    private void validate(LibraryEvent libraryEvent) {
        if (libraryEvent.getLibraryEventId() == null) {
           throw new IllegalArgumentException("Library Event Id is required");
        }

        log.info("Looking for LibraryEvent with id : {}", libraryEvent.getLibraryEventId());

        Optional<LibraryEvent> libraryEventToUpdate = libraryEventsRepository.findById(libraryEvent.getLibraryEventId());

        log.info("Processing : {}", libraryEventToUpdate);

        if (libraryEventToUpdate.isEmpty()) {
            throw new IllegalArgumentException("Invalid Library Event");
        }
    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);

        this.libraryEventsRepository.save(libraryEvent);

       log.info("Successfully saved library event : {}", libraryEvent);
    }
}
