package com.korisnik;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KorisnikRepo extends MongoRepository<Korisnik, String> {
}
