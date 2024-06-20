package com.rentme.app.configuration;

import com.rentme.app.event.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder.name(Topics.ORDER_CONFIRMATION.name()).build();
    }

}
