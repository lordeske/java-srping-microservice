package com.notifikacija.notifikacija;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotifikacijaRepo extends MongoRepository<Notifikacija, String> {
}
