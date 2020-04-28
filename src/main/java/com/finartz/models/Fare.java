package com.finartz.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
@Indexed
public class Fare {

    @Id
    private String flightno;

    private Float fare;

    public Fare(String flightno, Float fare) {
        this.flightno = flightno;
        this.fare = fare;
    }

    public Fare() {
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }
}
