package com.finartz.services;

import com.finartz.controllers.requestmodels.FlightSaveModel;
import com.finartz.models.*;
import com.finartz.repos.*;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SaveService {

    private float initialFare =100f;

    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    @Autowired
    FareRepo fareRepo;

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    FlightRepo flightRepo;

    @Autowired
    AirlineCompanyRepo airlineCompanyRepo;

    @Autowired
    AirportRepo airportRepo;

    @Autowired
    RouteRepo routeRepo;

    @Transactional
    public ResponseEntity cancelTicket(String ticketNumber){
        entityManager = emf.createEntityManager();

        Optional<Ticket> dbTicket = ticketRepo.findById(ticketNumber);
        if (!dbTicket.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bilet Mevcut Değil");

         dbTicket.get().setIssold(2);
         entityManager.merge(dbTicket.get());
         return ResponseEntity.status(HttpStatus.OK).body("Bilet İptal Edildi");
    }

    @Transactional
    public  ResponseEntity taketicket(Ticket ticket){

        Optional<Fare> mfare = fareRepo.findById(ticket.getFlightno());
        initialFare = mfare.get().getFare();

        Optional<Ticket> dbTicket = ticketRepo.findById(ticket.getTicketnumber());
        if (!dbTicket.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bilet Mevcut Değil");

        if(dbTicket.isPresent() && dbTicket.get().getIssold() == 1)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bilet Satılmış");


        Float allTicketCounts = Float.valueOf(ticketRepo.findAll().size());
        Float soldTicketCounts = Float.valueOf(ticketRepo.getsoldTickets(1).size());
        int percantage = (int) ((soldTicketCounts/allTicketCounts)*100);
        soldTicketCounts++;
        int updatedPercentage = (int) ((soldTicketCounts/allTicketCounts)*100);


        int percantageResidual = percantage/10;
        int updatedResidual =updatedPercentage/10;

        if(updatedResidual>percantageResidual && updatedPercentage/10>0){
            initialFare += initialFare*0.1;
            mfare.get().setFare(initialFare);
            fareRepo.save(mfare.get());
        }


        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Ticket ticket1 =entityManager.find(Ticket.class,ticket.getTicketnumber());
        entityManager.lock(ticket1, LockModeType.PESSIMISTIC_WRITE);
        ticket1.setIssold(1);
        ticket1.setFare(initialFare);
        ticket1.setPassangername(ticket.getPassangername());
        entityManager.getTransaction().commit();
        entityManager.close();

        return ResponseEntity.status(HttpStatus.OK).body("Bilet Alındı");
    }

    @Transactional
    public ResponseEntity saveFlight(FlightSaveModel flightSaveModel){

        Optional<AirlineCompany> airlineCompany = airlineCompanyRepo.findById(flightSaveModel.getCompanyname());
        if (!airlineCompany.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Havalimanı Tanımlı Değil");

        Optional<Flight> dbFlight = flightRepo.findById(flightSaveModel.getFlightnumber());
        if(dbFlight.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uçuş kodu  zaten tanımlı");

        Flight flight = new Flight();
        flight.setAirlineCompany(airlineCompany.get());
        flight.setFlightnumber(flightSaveModel.getFlightnumber());
        flight.setArrival(flightSaveModel.getArrival());
        flight.setDeparture(flightSaveModel.getDeparture());
        flightRepo.save(flight);

        return ResponseEntity.ok("Uçuş firmaya tanımlandı");
    }

    @Transactional
    public ResponseEntity saveAirport(Airport airport){
        JsonObject jsonObject = new JsonObject();

        Optional<Airport> dbAirport = airportRepo.findById(airport.getAiportcode());
        if (dbAirport.isPresent()){
            jsonObject.addProperty("hata","havalimanı zaten tanımlı");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject.toString());
        }
        airportRepo.save(airport);
        jsonObject.addProperty("mesaj","Havalimanı Kaydedildi");
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }

    @Transactional
    public ResponseEntity saveAirlineCompany(AirlineCompany airlineCompany){
        JsonObject jsonObject = new JsonObject();
        Optional<AirlineCompany> dbairlineCompany = airlineCompanyRepo.findById(airlineCompany.getCompanyname());
        if(dbairlineCompany.isPresent()){
            jsonObject.addProperty("hata","Havalyolu Şirketi Zaten Tanımlı");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject.toString());
        }
        airlineCompanyRepo.save(airlineCompany);
        jsonObject.addProperty("mesaj","Havayolu Şirketi Kaydedildi");
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }

    @Transactional
    public ResponseEntity saveRoute(Route route){
        JsonObject jsonObject = new JsonObject();
        Optional<Route> routedb = routeRepo.findById(route.getRouteId());
        if(routedb.isPresent()){
            jsonObject.addProperty("hata","Rota kodu zaten tanımlı");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject.toString());
        }
        routeRepo.save(route);
        jsonObject.addProperty("mesaj","Rota Kaydedild");
        return  ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }


}
