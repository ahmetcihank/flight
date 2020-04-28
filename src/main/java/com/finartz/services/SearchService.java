package com.finartz.services;


import com.finartz.models.*;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SearchService {

    /*App Start' ta bunşarı initialize et Vakit kalırsa Sorguları generic yap*/
    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager entityManager;
    private FullTextEntityManager fullTextEntityManager;


    @Transactional
    public List<Ticket> ticketSearch(String key) throws InterruptedException {
        entityManager = emf.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();


        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Ticket.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("ticketnumber")
                .matching(key + "*")
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ticket.class);
        List<Ticket> tickets =jpaQuery.getResultList();
        return tickets;

    }

    @Transactional
    public List<Airport> airportSearch(String key) throws InterruptedException {
        entityManager = emf.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();


        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Airport.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("aiportcode")
                .matching(key + "*")
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Airport.class);
        List<Airport> airports =jpaQuery.getResultList();
        return airports;

    }



    @Transactional
    public List<Route> routeSearch(String routeid) throws InterruptedException {
        entityManager = emf.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();


        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Route.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("routeId")
                .matching(routeid + "*")
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Route.class);
        List<Route> routes =jpaQuery.getResultList();
        return routes;

    }


    @Transactional
    public  List<AirlineCompany> companySearch(String key) throws InterruptedException {
        entityManager = emf.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(AirlineCompany.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("companyname")
                .matching(key + "*")
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, AirlineCompany.class);
        List<AirlineCompany> airlineCompanies;
        airlineCompanies =jpaQuery.getResultList();
        return airlineCompanies;
    }

      @Transactional
      public   List<Flight> flightSearch(String companyName, String flightkey) throws InterruptedException {
        entityManager = emf.createEntityManager();
        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();


        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Flight.class)
                .get();


        Query flightQ = queryBuilder.keyword()
                .wildcard()
                .onFields("flightnumber")
                .matching(flightkey + "*")
                .createQuery();

        /*
        Query campaignQ = queryBuilder.keyword().wildcard()
                .onField("companyname")
                .matching(companyName)
                .createQuery();

        Query luceneQuery = queryBuilder
                .bool()
                .should( flightQ )
                .must( campaignQ )
                .createQuery();*/

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(flightQ, Flight.class);
        List<Flight> flights =jpaQuery.getResultList();
        return flights;
    }

}
