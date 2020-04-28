package com.finartz.repos;

import com.finartz.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight, String> {
}
