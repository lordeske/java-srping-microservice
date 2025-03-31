package com.porudzbina;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StavkePorudzbineService {



    private final  StavkePorudzbineRepo stavkePorudzbineRepo;

    private final  StavkePorudzbineMapper stavkePorudzbineMapper;


    public Integer sacuvajStavkePorudzbine(StavkePorudzbineReq stavkePorudzbineReq) {



        StavkePorudzbine stavkePorudzbine = stavkePorudzbineMapper.uStavkePorudzbine(stavkePorudzbineReq);
        return stavkePorudzbineRepo.save(stavkePorudzbine).getId();




    }
}
