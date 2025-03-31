package com.porudzbina.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPorudzbineTopicConfig {


    @Bean
    public NewTopic porudzbinaTopic()
    {

        return TopicBuilder.name("porudzbina-topic")
                .build();

    }


}
