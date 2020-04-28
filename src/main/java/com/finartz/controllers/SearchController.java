package com.finartz.controllers;



import com.finartz.services.SearchService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


@RestController
@RequestMapping("/search")
public class SearchController {

    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager entityManager;


    private FullTextEntityManager fullTextEntityManager;

    @Autowired
    SearchService searchService;

    @GetMapping("/companysearch")
    public ResponseEntity getCompanySearch(@RequestParam("key") String key) throws InterruptedException {
       return ResponseEntity.ok(searchService.companySearch(key));
    }

    @GetMapping("/flightsearch")
    public ResponseEntity getflightSearch(@RequestParam("companyname")String companyname, @RequestParam("flightkey") String flightkey) throws InterruptedException {
            return ResponseEntity.ok(searchService.flightSearch(companyname,flightkey));
    }

    @GetMapping("/routesearch")
    public ResponseEntity getRouteSearch(@RequestParam("routeid") String routeid) throws InterruptedException {
        return ResponseEntity.ok(searchService.routeSearch(routeid));
    }

    @GetMapping("/airportsearch")
    public ResponseEntity getAirportSearch(@RequestParam("key") String key) throws InterruptedException {
        return ResponseEntity.ok(searchService.airportSearch(key));
    }

    @GetMapping("/ticketsearch") ResponseEntity ticketSearch(@RequestParam("key") String key) throws InterruptedException{
        return ResponseEntity.ok(searchService.ticketSearch(key));
    }
}
