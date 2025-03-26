package com.proizvod;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProizovdRepo extends JpaRepository<Proizvod, Integer> {
    List<Proizvod> findAllByIdInOrdrById(List<Integer> listIDProizvoda);
}
