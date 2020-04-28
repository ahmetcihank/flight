package com.finartz.repos;

import com.finartz.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, String> {
    @Query("FROM Ticket u WHERE  u.issold = :issold")
    List<Ticket> getsoldTickets(Integer issold);

}
