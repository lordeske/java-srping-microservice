package com.placanje.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotifikacijaProducer {



    private final KafkaTemplate<String, NotifikacijaReq> kafkaTemplate;


    public void posaljiNotifikaciju(NotifikacijaReq notifikacijaReq)
    {

        log.info("Saljem notifikaciju");


        Message<NotifikacijaReq> notifikacijaReqMessage = MessageBuilder
                .withPayload(notifikacijaReq)
                .setHeader(KafkaHeaders.TOPIC, "placanje-topic")
                .build();


        kafkaTemplate.send(notifikacijaReqMessage);

    }







}
