package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketCostSum;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

public interface ITicketService extends IGenericService<Ticket>{
    
    @Transactional
    void deleteAll(Integer seanceId);
    
   //BigDecimal getTicketsCostSum(TicketWithAllDataFilter filter); // сделать после того как будут заказы
    
    
    List<TicketWithAllData> search(Integer seanceId, Status status);
    
    TicketWithAllData getByTicketId(Integer ticketId);
    
    @Transactional
   void ChangeStatusOfATicketWithAllData(Integer ticketId, Status status);

}
