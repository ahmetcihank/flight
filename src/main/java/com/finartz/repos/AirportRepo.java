package com.finartz.repos;

import com.finartz.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepo extends JpaRepository<Airport, String> {
}
