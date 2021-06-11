package com.learnkafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEventsControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    LibraryEventProducer libraryEventProducer;

    @Test
    void postLibraryEvent() throws Exception {
        // Given
        Book book = Book.builder()
                .bookId(123)
                .bookAuthor("Nacho")
                .bookName("Kafka using Spring Boot")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
            .libraryEventId(1)
            .book(book)
            .build();

        String json = objectMapper.writeValueAsString(libraryEvent);

        doNothing().when(libraryEventProducer).sendLibraryEventWithSpecificTopic(isA(LibraryEvent.class));

        // When
        mockMvc.perform(post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Then
    }

    @Test
    void postLibraryEvent_4xx() throws Exception {
        // Given
        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(1)
                .book(null)
                .build();

        String json = objectMapper.writeValueAsString(libraryEvent);

        doNothing().when(libraryEventProducer).sendLibraryEventWithSpecificTopic(isA(LibraryEvent.class));

        // When
        mockMvc.perform(post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        // Then
    }
}
