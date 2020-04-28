package com.finartz.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Indexed
public class Airport {

    @Id
    private String aiportcode;

    private String airportname;

    public Airport() {
    }

    public Airport(String aiportcode, String airportname) {
        this.aiportcode = aiportcode;
        this.airportname = airportname;
    }

    public String getAiportcode() {
        return aiportcode;
    }

    public void setAiportcode(String aiportcode) {
        this.aiportcode = aiportcode;
    }

    public String getAirportname() {
        return airportname;
    }

    public void setAirportname(String airportname) {
        this.airportname = airportname;
    }
}
