package com.finartz.repos;

import com.finartz.models.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepo extends JpaRepository<Fare, String> {

}
