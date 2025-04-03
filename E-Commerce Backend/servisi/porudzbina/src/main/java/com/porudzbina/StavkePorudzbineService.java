package com.porudzbina;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StavkePorudzbineService {



    private final  StavkePorudzbineRepo stavkePorudzbineRepo;

    private final  StavkePorudzbineMapper mapper;


    public Integer sacuvajStavkePorudzbine(StavkePorudzbineReq stavkePorudzbineReq) {



        StavkePorudzbine stavkePorudzbine = mapper.uStavkePorudzbine(stavkePorudzbineReq);
        return stavkePorudzbineRepo.save(stavkePorudzbine).getId();




    }

    public List<StavkePorudzbineResp> nadjiPoOrderIDu(Integer orderID) {


        return stavkePorudzbineRepo.findAllByPorudzbina_Id(orderID)
                .stream()
                .map(mapper :: uStavkePorudzbineResp)
                .collect(Collectors.toList());

    }
}
