package com.porudzbina;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ProizvodKlijent {


    @Value("${application.config.proizvod-url}")
    private String proizvodURL;


    private final RestTemplate restTemplate;


    public List<KupljeniProizovdiResp> kupiProizvode(List<KupovinaZahtjev> zahtjevBody)
    {

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity <List<KupovinaZahtjev>> zahtjevEntitet =
                new HttpEntity<>(zahtjevBody, headers);


        ParameterizedTypeReference<List<KupljeniProizovdiResp>> odgovorType
                = new ParameterizedTypeReference<>(){};



        ResponseEntity<List<KupljeniProizovdiResp>> responseEntity = restTemplate
                .exchange(proizvodURL + "/kupi" , HttpMethod.POST,
                        zahtjevEntitet, odgovorType);


        if(responseEntity.getStatusCode().isError())
        {

            throw new KupovinaNeuspesnaException("Greška prilikom kupovine proizvoda. Status: " + responseEntity.getStatusCode());

        }

        return responseEntity.getBody();
    }


}
