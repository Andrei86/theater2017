package com.shalkevich.andrei.training2017.dao.impl.db;

import java.util.List;

import com.shalkevich.andrei.training2017.dao.impl.db.filter.BookingFilter;
import com.shalkevich.andrei.training2017.datamodel.Booking;

public interface IBookingDao extends IGenericDao<Booking>{
    
    List<Booking> getByCustomerId(Integer customerId);
    
    Booking getByTicketId(Integer ticketId);


}
