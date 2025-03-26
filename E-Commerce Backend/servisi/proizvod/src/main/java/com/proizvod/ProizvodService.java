package com.proizvod;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.classfile.Opcode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProizvodService {


    @Autowired
    private  ProizovdRepo proizovdRepo;

    private  ProizvodMapper mapper;

    public Integer kreirajProizovd(ProzivodReq prozivodReq) {


        Proizvod proizvod = mapper.uProizvod(prozivodReq);
        proizovdRepo.save(proizvod);
        return proizvod.getId();
    }

    public List<KupljeniProizovdiResp> kupiProizvode(List<KupljeniProizovdiReq> kupljeniProizvodiReq) {

        List<Integer> listIDProizvoda = kupljeniProizvodiReq.stream()
                .map(KupljeniProizovdiReq::idProizvoda)
                .toList();


        List<Proizvod> sviProizvodiPoID = proizovdRepo.findAllByIdInOrdrById(listIDProizvoda);


        if(listIDProizvoda.size() != sviProizvodiPoID.size() )
        {


            throw  new ProizvodKupovinaException("Nekog proizvoda nema na stanju");


        }


        List <KupljeniProizovdiReq> storedZahtjev = kupljeniProizvodiReq
                .stream()
                .sorted(Comparator.comparing(KupljeniProizovdiReq::idProizvoda))
                .toList();


        List <KupljeniProizovdiResp> kupljeniProizovdi = new ArrayList<>();

        for(int i =0  ; i < storedZahtjev.size() ; i++ )
        {

         /// ovde dodati

        }


    }

    public ProzivodResp proizovdPoID(Integer idProizvoda) {

        Optional<Proizvod> proizvod = proizovdRepo.findById(idProizvoda);

        if(proizvod.isPresent())
        {
            Proizvod dobijeniProizvod = proizvod.get();
            return mapper.uResp(dobijeniProizvod);

        }
        else
        {
            throw new ProizovdNijeProdnadjenException("Proizvod sa ID-jem "+ idProizvoda + " nije pronadjen");
        }
    }

    public List<ProzivodResp> sviProzivodi() {

        return proizovdRepo.findAll()
                .stream()
                .map(proizvod -> mapper.uResp(proizvod))
                .collect(Collectors.toList());
    }






}
