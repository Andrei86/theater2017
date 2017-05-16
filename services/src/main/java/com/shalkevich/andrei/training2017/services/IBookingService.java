package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.BookingFilter;
import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;


public interface IBookingService extends IGenericService<Booking> {
	
	
	/*List<TicketWithAllData> searchTicketByCustomerAndDates(Integer customerId, Date dateFrom, Date dateTo);

	
	List<Booking> searchBookingByCustomerAndDates(Integer customerId, Date dateFrom, Date dateTo);// для подсчета стоимости билетов
	
	List<Booking> searchBookingByCustomer(Integer customerId);*/
	
	// Новый добавленный фильтр
	
	//List<Booking> search(BookingFilter filter); // удалить

	Booking findByTicketId(Integer ticketId);// для кассира sql
	
	List<Booking> findByCustomerId(Integer customerId);// для юзера
	
	List<Booking> findByCustomerIdAndDates(Integer customerId, Date dateFrom, Date dateTo);
	
	BigDecimal getBookingCostSum(List<Booking> list);
	
	/*List<TicketWithAllData> searchTicketByCustomer(Integer customerId);*/
	
	//BigDecimal getCostTicketSum(Integer customerId, Date dateFrom, Date dateTo); // перенесли в ticket
	
	/*@Transactional
	Booking CreateBooking(Integer customerId, Integer ticketId);
	
	@Transactional
	void DeleteBooking(Integer customerId, Integer ticketId);*/
	
}
