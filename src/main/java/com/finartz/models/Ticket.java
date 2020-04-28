package com.finartz.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Indexed
public class Ticket  implements Serializable {

    @Id
    private String ticketnumber;

    private String  departure;

    private String arrival;

    private float fare;

    private Integer issold;

    private String passangername;

    private Integer seatnumber;

    private String flightno;

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public Ticket() {
    }

    public Ticket(String ticketnumber, String departure, String arrival, float fare, Integer issold, String passangername, Integer seatnumber, String flightno) {
        this.ticketnumber = ticketnumber;
        this.departure = departure;
        this.arrival = arrival;
        this.fare = fare;
        this.issold = issold;
        this.passangername = passangername;
        this.seatnumber = seatnumber;
        this.flightno = flightno;
    }

    public String getTicketnumber() {
        return ticketnumber;
    }

    public void setTicketnumber(String ticketnumber) {
        this.ticketnumber = ticketnumber;
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

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public Integer getIssold() {
        return issold;
    }

    public void setIssold(Integer issold) {
        this.issold = issold;
    }

    public String getPassangername() {
        return passangername;
    }

    public void setPassangername(String passangername) {
        this.passangername = passangername;
    }

    public Integer getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(Integer seatnumber) {
        this.seatnumber = seatnumber;
    }
}
