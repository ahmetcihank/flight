package com.finartz.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
@Indexed
public class Route {

    @Id
    private String routeId;

    private String departure;

    private String arrival;

    public Route(String routeId, String departure, String arrival) {
        this.routeId = routeId;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Route() {
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
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
}
