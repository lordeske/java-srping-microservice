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

        List<Integer> proizvodiIDs = kupljeniProizvodiReq.stream()
                .map(KupljeniProizovdiReq::idProizvoda)
                .toList(); // 1,2,3,4


        List<Proizvod> proizvodiNaStanju = proizovdRepo.findAllByIdInOrdrById(proizvodiIDs);


        if(proizvodiIDs.size() != proizvodiNaStanju.size() )
        {


            throw  new ProizvodKupovinaException("Nekog proizvoda nema na stanju");


        }


        List <KupljeniProizovdiReq> sortiraniZahtjevi = kupljeniProizvodiReq
                .stream()
                .sorted(Comparator.comparing(KupljeniProizovdiReq::idProizvoda))
                .toList();


        List <KupljeniProizovdiResp> kupljeniProizovdi = new ArrayList<>();

        for(int i =0  ; i < sortiraniZahtjevi.size() ; i++ )
        {

            Proizvod proizvod = proizvodiNaStanju.get(i);

            KupljeniProizovdiReq proizovdiReq = sortiraniZahtjevi.get(i);


            if(proizvod.getBrojDostupnih() < proizovdiReq.kolicina())
            {

                throw new NemaProizvodaNaStanjuException("Prozivod "+ proizvod.getNazivProizvoda() + " nema toliko zaliha");

            }

            double noviBrojDostupnosti = proizvod.getBrojDostupnih() - proizovdiReq.kolicina();

            proizvod.setBrojDostupnih(noviBrojDostupnosti);
            proizovdRepo.save(proizvod);


            kupljeniProizovdi.add(mapper.uKupljeniOdg(proizvod, proizovdiReq.kolicina()));

        }

        return kupljeniProizovdi;

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
