package com.porudzbina;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkePorudzbineRepo extends JpaRepository<StavkePorudzbine,Integer > {
    List<StavkePorudzbine> findAllByPorudzbina_Id(Integer orderID);
}
