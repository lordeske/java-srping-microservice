package com.notifikacija.notifikacija;



import com.notifikacija.Kafka.Placanje.PotvrdaPlacanja;
import com.notifikacija.Kafka.Porudzbina.PotvrdaPorudzbine;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notifikacija {

    @Id
    private String id;


    private TipNotifikacije tipNotifikacije;

    private LocalDateTime datumNotifikacije;


    private PotvrdaPorudzbine potvrdaPorudzbine;

    private PotvrdaPlacanja potvrdaPlacanja;








}
