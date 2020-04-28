package com.finartz.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Indexed
public class AirlineCompany {

    @Id
    private String companyname;

    private String headquarter;

    @OneToMany(mappedBy = "airlineCompany")
    List<Flight> flights;


    public AirlineCompany() {
    }

    public AirlineCompany(String companyname, String headquarter, List<Flight> flights) {
        this.companyname = companyname;
        this.headquarter = headquarter;
        this.flights = flights;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
