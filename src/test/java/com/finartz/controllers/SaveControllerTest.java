package com.finartz.controllers;

import com.finartz.controllers.requestmodels.FlightSaveModel;
import com.finartz.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaveControllerTest {

    @Autowired
    SaveController saveController;

    @Autowired
    SearchController searchController;


    @Test
    void saveAirlineCompany() {
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setCompanyname("thy");
        airlineCompany.setHeadquarter("Ankara");
        ResponseEntity responseEntity =  saveController.saveAirlineCompany(airlineCompany);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void saveFlight() {
        FlightSaveModel flightSaveModel = new FlightSaveModel();
        flightSaveModel.setCompanyname("thy");
        flightSaveModel.setArrival("Ankara");
        flightSaveModel.setDeparture("istanbul");
        flightSaveModel.setFlightnumber("test33");
        flightSaveModel.setHeadquarter("istanbul");
        ResponseEntity responseEntity = saveController.saveFlightcont(flightSaveModel);
        assertEquals(responseEntity.getStatusCode(),ResponseEntity.status(HttpStatus.OK));
    }

    @Test
    void saveAirport() {
        Airport airport = new Airport();
        airport.setAiportcode("ESB");
        airport.setAirportname("Esenboga");
        ResponseEntity responseEntity = saveController.saveAirport(airport);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }


    @Test
    void saveRoute() {
        Route route = new Route();
        route.setArrival("Ankara");
        route.setDeparture("Berlin");
        route.setRouteId("test113");
        ResponseEntity responseEntity = saveController.saveRoute(route);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void takeTicket(){
        Ticket ticket = new Ticket();
        ticket.setPassangername("Ali");
        ticket.setTicketnumber("e18");
        ticket.setFlightno("123");
        ResponseEntity responseEntity =saveController.takeTicket(ticket);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void cancelTicket(){
        ResponseEntity responseEntity = saveController.cancelTicket("e11");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void searchCompany() throws InterruptedException {
        ResponseEntity responseEntity = searchController.getCompanySearch("th");
        List<AirlineCompany> airlineCompanies = (List<AirlineCompany>) responseEntity.getBody();
        List<AirlineCompany>testair =
                airlineCompanies.stream().filter(p -> p.getCompanyname().matches(".*"+"th"+".*")).
                        collect(Collectors.toList());
        assertEquals(testair, airlineCompanies);
    }


    @Test
    void searchFlight() throws InterruptedException {
        ResponseEntity responseEntity = searchController.getflightSearch("thy","te");
        List<Flight>flights = (List<Flight>) responseEntity.getBody();
        List<Flight>testflight=
                flights.stream().filter(p -> p.getFlightnumber().matches(".*"+"th"+".*")).
                        collect(Collectors.toList());
        assertEquals(flights, testflight);
    }

    @Test
    void searchroute() throws InterruptedException {
        ResponseEntity responseEntity = searchController.getRouteSearch("te");
        List<Route>routes = (List<Route>) responseEntity.getBody();
        List<Route>testroutes=
                routes.stream().filter(p -> p.getRouteId().matches(".*"+"te"+".*")).
                        collect(Collectors.toList());
        assertEquals(routes, testroutes);
    }

    @Test
    void airportSearch() throws InterruptedException {
        ResponseEntity responseEntity = searchController.getRouteSearch("es");
        List<Airport>airports = (List<Airport>) responseEntity.getBody();
        List<Airport>testairports=
                airports.stream().filter(p -> p.getAirportname().matches(".*"+"es"+".*")).
                        collect(Collectors.toList());
        assertEquals(airports, testairports);
    }
}