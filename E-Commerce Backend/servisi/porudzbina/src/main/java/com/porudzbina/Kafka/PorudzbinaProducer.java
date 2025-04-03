package com.porudzbina.Kafka;


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
public class PorudzbinaProducer {


    private final KafkaTemplate <String,PotvrdaPorudzbine> kafkaTemplate;





    public void posaljiPotvrduPorudzbine(PotvrdaPorudzbine potvrdaPorudzbine)
    {

        log.info("Saljem potvrdi porudzbine");

        Message<PotvrdaPorudzbine> porudzbineMessage = MessageBuilder.withPayload(
                potvrdaPorudzbine)
                .setHeader(KafkaHeaders.TOPIC, "porudzbina-topic")
                .build();


        kafkaTemplate.send(porudzbineMessage);
    }



}
