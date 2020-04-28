package com.finartz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
@Indexed
public class Flight {

    @Id
    private String flightnumber;

    private String departure;

    private String arrival;

    @JsonIgnore
    @ManyToOne(targetEntity = com.finartz.models.AirlineCompany.class)
    @JoinColumn(name = "companyname", referencedColumnName = "companyname")
    @IndexedEmbedded(depth=1)
    private AirlineCompany airlineCompany;

    public Flight(String flightnumber, String departure, String arrival, AirlineCompany airlineCompany) {
        this.flightnumber = flightnumber;
        this.departure = departure;
        this.arrival = arrival;
        this.airlineCompany = airlineCompany;
    }

    public Flight() {
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
}
