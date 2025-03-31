package com.porudzbina;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorudzbinaRepo extends JpaRepository<Porudzbina, Integer> {
}
