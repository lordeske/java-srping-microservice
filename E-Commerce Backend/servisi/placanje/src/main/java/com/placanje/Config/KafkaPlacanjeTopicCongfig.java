package com.placanje.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPlacanjeTopicCongfig {


    @Bean

    public NewTopic placanjeTopic()
    {

        return TopicBuilder.name("placanje-topic").build();
    }



}
