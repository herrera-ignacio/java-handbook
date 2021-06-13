package com.learnkafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
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
