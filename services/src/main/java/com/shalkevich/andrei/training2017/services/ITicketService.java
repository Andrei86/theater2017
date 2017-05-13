package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketService extends IGenericService<Ticket>{
    
/*    @Transactional
    void deleteBySeanceId(Integer seanceId);    // нужен ли он?
*/    
    List<Ticket> search(TicketFilter filter);
    
    BigDecimal getTicketCostSum(List<Ticket> ticketList);
    
    @Transactional
    void changeStatusOfATicket(Integer ticketId, Status status); // не надо дергать TicketWithAllData!
    
    @Transactional
    Ticket buyingATicket(Integer ticketId); // покупка билета в кассе
    
    @Transactional
    Ticket buyOutTicket(Integer bookingId); // выкуп забронированного билета
    
    @Transactional
    Ticket returningMoneyForATicket(Integer ticketId); // возврат денег за билет и возврат билета в продажу
    
    @Transactional
    Ticket returningBookedTicketOnSale(Integer ticketId); // возврат кассиром невыкупленного билета в продажу менее 20 мин до нач сеанса
}

