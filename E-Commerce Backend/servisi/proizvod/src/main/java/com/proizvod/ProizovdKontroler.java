package com.proizvod;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proizvod")
public class ProizovdKontroler {


    @Autowired
    private  ProizvodService proizvodService;


    @PostMapping
    private ResponseEntity<Integer> kreirajProizvod(
            @RequestBody @Valid ProzivodReq prozivodReq
    )
    {


        return ResponseEntity.ok(proizvodService.kreirajProizovd(prozivodReq));


    }


    @PostMapping("/kupi")
    private ResponseEntity<List<KupljeniProizovdiResp>> kupiProizvode(

            @RequestBody @Valid List<KupljeniProizovdiReq> kupljeniProizvodi

            )
    {


        return ResponseEntity.ok(proizvodService.kupiProizvode(kupljeniProizvodi));

    }


    @GetMapping("/{proizvod-id}")
    public ResponseEntity<ProzivodResp> proizovdPoID(
            @PathVariable("proizvod-id") Integer idProizvoda
    )
    {


      return ResponseEntity.ok(proizvodService.proizovdPoID(idProizvoda));

    }


    @GetMapping
    public ResponseEntity<List<ProzivodResp>> sviProizvodi()
    {


        return  ResponseEntity.ok(proizvodService.sviProzivodi());

    }

}
