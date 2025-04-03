package com.porudzbina;


import org.springframework.stereotype.Service;

@Service
public class StavkePorudzbineMapper {


    public StavkePorudzbine uStavkePorudzbine(StavkePorudzbineReq stavkePorudzbineReq) {


        return StavkePorudzbine.builder()

                .kolicina(stavkePorudzbineReq.kolicina())
                .idProizvoda(stavkePorudzbineReq.proizvodId())
                .porudzbina(Porudzbina.builder()
                                .id(stavkePorudzbineReq.idPorudzbine())


                        .build())
                .build();

    }

    public StavkePorudzbineResp uStavkePorudzbineResp(StavkePorudzbine stavkePorudzbine) {


        return new StavkePorudzbineResp(
                stavkePorudzbine.getId(),
                stavkePorudzbine.getKolicina()
        );




    }

}
