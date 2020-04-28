package com.finartz.controllers;


import com.finartz.controllers.requestmodels.FlightSaveModel;
import com.finartz.models.AirlineCompany;
import com.finartz.models.Airport;
import com.finartz.models.Route;
import com.finartz.models.Ticket;
import com.finartz.services.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
public class SaveController {

    @Autowired
    SaveService saveService;

    @PostMapping("/saveflight")
    public ResponseEntity saveFlightcont(@RequestBody FlightSaveModel flightSaveModel){
        return saveService.saveFlight(flightSaveModel);
    }

    @PostMapping("/saveairport")
    public ResponseEntity saveAirport(@RequestBody Airport airport){
        return saveService.saveAirport(airport);
    }

    @PostMapping("/savecompany")
    public  ResponseEntity saveAirlineCompany(@RequestBody AirlineCompany airlineCompany){
        return saveService.saveAirlineCompany(airlineCompany);
    }

    @PostMapping("/saveroute")
    public ResponseEntity saveRoute(@RequestBody Route route){
        return saveService.saveRoute(route);
    }

    @PostMapping("/taketicket")
    public ResponseEntity takeTicket(@RequestBody Ticket ticket){
        return saveService.taketicket(ticket);
    }

    @PostMapping("/cancelticket/{ticketno}")
    public ResponseEntity cancelTicket(@PathVariable("ticketno") String ticketNo){
        return saveService.cancelTicket(ticketNo);
    }

}
