package com.shalkevich.andrei.training2017.webapp.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.ITicketService;
import com.shalkevich.andrei.training2017.webapp.models.BookingModel;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

	@Inject
	IBookingService bookingService;
	
	@Inject
	ICustomerService customerService;
	
	@Inject
	ITicketService ticketService;
	
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBooking(@PathVariable(value = "id") Integer bookingIdParam) {
        bookingService.delete(bookingIdParam);
        return new ResponseEntity<IdModel>(HttpStatus.OK); // обратить внимание на статус
    
    }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getByParameters(@RequestParam(required = false) Integer customer, Integer ticket,
			Date dateFrom, Date dateTo) {
		try {
			List<Booking> bookingList = null;

			BookingModel bookingModel = null;

			List<BookingModel> bookingModelList = new ArrayList<>();

			Booking booking = null;

			if (ticket != null) {
				booking = bookingService.findByTicketId(ticket);

			} else if (customer != null) {
				if (dateFrom != null && dateTo != null)
					bookingList = bookingService.findByCustomerIdAndDates(customer, dateFrom, dateTo);
				else
					bookingList = bookingService.findByCustomerId(customer);

			} else
				bookingService.getAll();

			if (booking != null) {
				bookingModel = entity2model(booking);
				return new ResponseEntity<BookingModel>(bookingModel, HttpStatus.OK);

			} else if (!bookingList.isEmpty()) {
				for (Booking b : bookingList)
					bookingModelList.add(entity2model(b));

				return new ResponseEntity<List<BookingModel>>(bookingModelList, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (UnsupportedOperationException e) {

			String msg = "You must insert parameters for booking search like ticket, customer, dateFrom, dateTo";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		} catch (NullPointerException e) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer bookingIdParam) {
		try {
			Booking booking = bookingService.get(bookingIdParam);
			BookingModel bookingModel = new BookingModel();
			bookingModel = entity2model(booking);

			return new ResponseEntity<BookingModel>(bookingModel, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createCustomer(@RequestBody BookingModel bookingModel) {
		try {
			Booking booking = model2entity(bookingModel);
			bookingService.save(booking);
			return new ResponseEntity<IdModel>(new IdModel(booking.getId()), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			String msg = "You must fill all fields for booking creating";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}
		
	
	
	private BookingModel entity2model(Booking booking) {
		BookingModel bookingModel = new BookingModel();

		bookingModel.setLogin(booking.getCustomer().getLogin());
		bookingModel.setTicketId(booking.getTicket().getId());
		bookingModel.setBookingDate(booking.getBookingDate().toString().substring(0, 16));

		return bookingModel;
	}

	private Booking model2entity(BookingModel bookingModel) {
		Booking booking = new Booking();

		booking.setCustomer(customerService.getByLogin(bookingModel.getLogin()));
		booking.setTicket(ticketService.get(bookingModel.getTicketId()));
		booking.setBookingDate(new Timestamp(new java.util.Date().getTime())/*valueOf(bookingModel.getBookingDate())*/);

		return booking;
	}

}
