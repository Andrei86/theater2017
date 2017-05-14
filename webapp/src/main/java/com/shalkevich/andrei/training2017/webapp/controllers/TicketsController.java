package com.shalkevich.andrei.training2017.webapp.controllers;

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
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.MovieTheater;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.IMovieService;
import com.shalkevich.andrei.training2017.services.IMovieTheaterService;
import com.shalkevich.andrei.training2017.services.ISeanceService;
import com.shalkevich.andrei.training2017.services.ITicketService;
import com.shalkevich.andrei.training2017.webapp.models.IdModel;
import com.shalkevich.andrei.training2017.webapp.models.TicketModel;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
	
	@Inject
	ISeanceService seanceService;

	@Inject
	IMovieTheaterService theaterService;

	@Inject
	IBookingService bookingService;

	@Inject
	IMovieService movieService;

	@Inject
	ITicketService ticketService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getByFilter(@RequestParam(required = false) Integer seance, String status) {

		List<Ticket> ticketList;

		List<TicketModel> ticketModelList = new ArrayList<>();

		TicketFilter ticketFilter = new TicketFilter();
		ticketFilter.setSeanceId(seance);
		try {
			if(status != null)
			ticketFilter.setStatus(Status.valueOf(status));
		} catch (/*NullPointerException*/ IllegalArgumentException e) {
			String msg = String.format("Status [%s] is not supported. Please use one of free, booked or busy", status);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	/*	catch (NullPointerException e) {
			String msg = "You must insert parameters for ticket search [seance], [status]";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}*/

		if (ticketFilter.isEmpty()) {
			String msg = "You must insert parameters for ticket search [seance], [status]";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		} else
			ticketList = ticketService.search(ticketFilter);

		if (ticketList.size() != 0) {
			for (Ticket ticket : ticketList)
				ticketModelList.add(entity2model(ticket));

			return new ResponseEntity<List<TicketModel>>(ticketModelList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer ticketIdParam) {
		try {
			Ticket ticket = ticketService.get(ticketIdParam);
			
			System.out.println(ticket);
			TicketModel ticketModel = new TicketModel();
			ticketModel = entity2model(ticket);

			return new ResponseEntity<TicketModel>(ticketModel, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createTicket(@RequestBody TicketModel ticketModel) {
		try {
			Ticket ticket = model2entity(ticketModel);
			ticketService.save(ticket);
			return new ResponseEntity<IdModel>(new IdModel(ticket.getId()), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			String msg = "You must fill by correct values all fields for ticket creating";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	public ResponseEntity<?> createTickets(@RequestBody TicketModel... ticketModels) {
		try {
			List<Ticket> ticketList = new ArrayList<>();
			List<IdModel> idModelList = new ArrayList<>();
			
			for(TicketModel modelOfTicket : ticketModels)
			{
			Ticket ticket = model2entity(modelOfTicket);
			ticketService.save(ticket);
			ticketList.add(ticket);
			idModelList.add(new IdModel(ticket.getId()));
			}
			
			return new ResponseEntity<List<IdModel>>(idModelList, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			String msg = "You must fill by correct values all fields for ticket creating";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // не будет этого метода
    public ResponseEntity<?> updateTicket(@RequestBody TicketModel ticketModel,
            @PathVariable(value = "id") Integer ticketIdParam) {
        
        Ticket ticket = ticketService.get(ticketIdParam);
        
        ticket.setStatus(Status.valueOf(ticketModel.getStatus()));

        ticketService.save(ticket);
        return new ResponseEntity<IdModel>(HttpStatus.OK);
    }*/
	
	@RequestMapping(value = "/buy/{id}", method = RequestMethod.PUT) // покупка без брони
    public ResponseEntity<?> buyTicket(@PathVariable(value = "id") Integer ticketIdParam) {
   
		TicketModel ticketModel = null;
        Ticket ticket = ticketService.buyingATicket(ticketIdParam);
        
        ticketModel = entity2model(ticket);
        
        return new ResponseEntity<TicketModel>(ticketModel ,HttpStatus.OK);
	    
	}
	
	@RequestMapping(value = "/returnmoney/{id}", method = RequestMethod.PUT) // покупка без брони
	public ResponseEntity<?> returnMoneyForTicket(@PathVariable(value = "id") Integer ticketIdParam) {

		Ticket ticket = null;
		TicketModel ticketModel = null;
		try
		{
		ticket = ticketService.returningMoneyForATicket(ticketIdParam);
		}
		catch(UnsupportedOperationException e)
		{
			String msg = "You can't return ticket less than 20 minutes before seance start.";
			return new ResponseEntity<String>(msg, HttpStatus.LOCKED);
		}
		ticketModel = entity2model(ticket);

		return new ResponseEntity<TicketModel>(ticketModel, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/buyout/{id}", method = RequestMethod.PUT) // покупка забронированного
    public ResponseEntity<?> buyOutBookedTicket(@PathVariable(value = "id") Integer bookingIdParam) {
   
		TicketModel ticketModel = null;
		Ticket ticket = null;
		
		try
		{
        ticket = ticketService.buyOutTicket(bookingIdParam);
		}catch(NullPointerException e)
		{
			String msg = "You must insert id of existing booking";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
        
        ticketModel = entity2model(ticket);
        
        return new ResponseEntity<TicketModel>(ticketModel ,HttpStatus.OK);
	    
	}
	
	@RequestMapping(value = "/returntosale/{id}", method = RequestMethod.PUT) // возврат забронированного в продажу
	public ResponseEntity<?> returnBookedTicketToSale(@PathVariable(value = "id") Integer ticketIdParam) {

		Ticket ticket = null;
		TicketModel ticketModel = null;
		try
		{
		ticket = ticketService.returningBookedTicketOnSale(ticketIdParam);
		}
		catch(UnsupportedOperationException e)
		{
			String msg = "You can't return booked ticket more than 20 minutes before seance start.";
			return new ResponseEntity<String>(msg, HttpStatus.LOCKED);
		}catch(NullPointerException e)
		{
			String msg = "You must insert valid ticket id";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
		
		ticketModel = entity2model(ticket);

		return new ResponseEntity<TicketModel>(ticketModel, HttpStatus.OK);

	}
	private TicketModel entity2model(Ticket ticket) {

		TicketModel ticketModel = new TicketModel();

		Seance seance = seanceService.get(ticket.getSeance().getId());
		MovieTheater movieTheater = seance.getMovieTheater();
		Movie movie = seance.getMovie();

		ticketModel.setId(ticket.getId());
		ticketModel.setSeance(ticket.getSeance().getId());
		ticketModel.setMovieTheater(movieTheater.getName());
		ticketModel.setMovie(movie.getTitle());
		ticketModel.setDate(seance.getDate().toString());
		ticketModel.setTime(seance.getTime().toString().substring(0, 5));
		ticketModel.setCost(ticket.getCost());
		ticketModel.setRow(ticket.getRow());
		ticketModel.setPlace(ticket.getPlace());
		ticketModel.setStatus(ticket.getStatus().name());

		return ticketModel;
	}

	private Ticket model2entity(TicketModel ticketModel) {
		Ticket ticket = new Ticket();

		Seance seance = seanceService.get(ticketModel.getSeance());

		ticket.setSeance(seance);
		ticket.setCost(ticketModel.getCost());
		ticket.setRow(ticketModel.getRow());
		ticket.setPlace(ticketModel.getPlace());
		//ticket.setStatus(Status.valueOf(ticketModel.getStatus()));
		ticket.setStatus(Status.free);

		return ticket;
	}
}
