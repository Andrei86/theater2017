package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketWithAllDataFilter;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.SeanceWithAllData;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketService extends IGenericService<Ticket>{
    
    @Transactional
    void deleteAll(Integer seanceId);
    
    BigDecimal getTicketsCostSum(TicketWithAllDataFilter filter);
    
   /* @Transactional
    TicketWithAllData BookingATicket(TicketWithAllData ticket, Customer c);*/
    
    //TicketWithAllData getByTicketId(Integer id);
    
    @Transactional
    Ticket PurchasingATicket(Ticket ticket);
    
    @Transactional
    Ticket BookingATicket(Ticket ticket, Customer c);
    
    @Transactional
    Ticket ExemptionOfATicket(Ticket ticket);
    
    //TicketWithAllData BookingATicket(Integer ticketId, Customer c);
    
    /*List<TicketWithAllData> getByCustomerId(Integer id, Date date1, Date date2); // свои билеты и еще подумать над стоимостью
	
	List<TicketWithAllData> getAll(Integer seanceId); // ? все билеты
	
	TicketCostSum getTicketCost(Integer seanceId);*/
    
    List<TicketWithAllData> search(TicketWithAllDataFilter filter);

}
