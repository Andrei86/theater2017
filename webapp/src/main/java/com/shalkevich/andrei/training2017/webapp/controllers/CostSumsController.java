package com.shalkevich.andrei.training2017.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalkevich.andrei.training2017.datamodel.Booking;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.ITicketService;
import com.shalkevich.andrei.training2017.webapp.models.BookingCostSumModel;

@RestController
@RequestMapping("/costsums")
public class CostSumsController {
	
	@Inject
	ITicketService ticketService;
	
	@Inject
	IBookingService bookingService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getById(@RequestParam(required = false) Integer... booking) {

			BookingCostSumModel bookingCostSumModel = new BookingCostSumModel();
			List<Booking> bookingList = new ArrayList<>();
			if(booking != null)
			{
			
			for(Integer bookingId : booking)
				bookingList.add(bookingService.get(bookingId));
			
			List<Ticket> ticketList = new ArrayList<>();
			
			for(Booking boo : bookingList)
				ticketList.add(boo.getTicket());
			
			bookingCostSumModel.setSumm(ticketService.getTicketCostSum(ticketList));
			
			return new ResponseEntity<BookingCostSumModel>(bookingCostSumModel, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("You must insert parameters booking to get bookings cost", HttpStatus.BAD_REQUEST);

	}

}
