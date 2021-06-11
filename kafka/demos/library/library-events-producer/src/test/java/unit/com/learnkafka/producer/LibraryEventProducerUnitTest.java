package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {

    @Mock
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    LibraryEventProducer eventProducer;

    @Test
    void sendLibraryEventWithSpecificTopic_failure() throws JsonProcessingException {
        // Given
        Book book = Book.builder()
                .bookId(123)
                .bookAuthor("Nacho")
                .bookName("Kafka using Spring Boot")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        SettableListenableFuture<SendResult<Integer, String>> future = new SettableListenableFuture<SendResult<Integer, String>>();

        future.setException(new RuntimeException("Exception calling Kafka"));

        // When
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        // Then
        assertThrows(Exception.class, () -> eventProducer.sendLibraryEventWithSpecificTopic(libraryEvent).get());
    }

    @Test
    void sendLibraryEventWithSpecificTopic_success() throws JsonProcessingException, ExecutionException, InterruptedException {
        // Given
        Book book = Book.builder()
                .bookId(123)
                .bookAuthor("Nacho")
                .bookName("Kafka using Spring Boot")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        SettableListenableFuture<SendResult<Integer, String>> future = new SettableListenableFuture<SendResult<Integer, String>>();

        String record = objectMapper.writeValueAsString(libraryEvent);

        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(), record);

        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1), 1, 1, 342, System.currentTimeMillis(), 1, 2);

        SendResult<Integer, String> sendResult = new SendResult<Integer, String>(producerRecord, recordMetadata);

        future.set(sendResult);

        // When
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        // Then
        ListenableFuture<SendResult<Integer, String>> listenableFuture = eventProducer.sendLibraryEventWithSpecificTopic(libraryEvent);

        SendResult<Integer, String> successRes = listenableFuture.get();

        assert successRes.getRecordMetadata().partition() == 1;
    }
}
