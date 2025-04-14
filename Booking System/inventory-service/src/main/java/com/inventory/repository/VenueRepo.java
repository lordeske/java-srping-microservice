package com.inventory.repository;

import com.inventory.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepo extends JpaRepository<Venue, Long> {
}
