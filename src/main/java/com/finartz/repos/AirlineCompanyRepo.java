package com.finartz.repos;

import com.finartz.models.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineCompanyRepo  extends JpaRepository<AirlineCompany, String> {
}
