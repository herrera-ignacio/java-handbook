package com.learnkafka.config;

import com.learnkafka.service.LibraryEventsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class LibraryEventsConsumerConfig {

    @Autowired
    LibraryEventsService libraryEventsService;

    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();

        configurer.configure(factory, kafkaConsumerFactory);

        factory.setConcurrency(3);

        factory.setErrorHandler((((thrownException, data) -> {
            log.info("Exception in consumerConfig is {}, and the record is {}", thrownException.getMessage(), data);
        })));

        factory.setRetryTemplate(retryTemplate());
        
        factory.setRecoveryCallback((context -> {
            if (context.getLastThrowable().getCause() instanceof RecoverableDataAccessException) {
                // Recovery logic
                log.info("Recovery logic");

                ConsumerRecord<Integer, String> consumerRecord = (ConsumerRecord<Integer, String>) context.getAttribute("record");

                libraryEventsService.handleRecovery(consumerRecord);
            } else {
                log.info("Non Recoverable Logic TODO");
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }

            return null;
        }));

        return factory;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplateObj = new RetryTemplate();

        // retryTemplateObj.setRetryPolicy(simpleRetryPolicy());
        retryTemplateObj.setRetryPolicy(specificExceptionRetryPolicy());
        retryTemplateObj.setBackOffPolicy(fixedBackOffPolicy());

        return retryTemplateObj;
    }

    private FixedBackOffPolicy fixedBackOffPolicy() {
        FixedBackOffPolicy fixedBackOffPolicyObj = new FixedBackOffPolicy();

        fixedBackOffPolicyObj.setBackOffPeriod(1000);

        return fixedBackOffPolicyObj;
    }

    private RetryPolicy simpleRetryPolicy() {
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();

        simpleRetryPolicy.setMaxAttempts(3);

        return simpleRetryPolicy;
    }

    private RetryPolicy specificExceptionRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionsMap = new HashMap<>();

        exceptionsMap.put(IllegalArgumentException.class, false);

        exceptionsMap.put(RecoverableDataAccessException.class, true);

        return new SimpleRetryPolicy(3, exceptionsMap, true);
    }
}
