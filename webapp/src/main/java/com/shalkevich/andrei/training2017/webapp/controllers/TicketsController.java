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
	
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // когда кассир продает через кассу
    public ResponseEntity<?> updateTicket(@RequestBody TicketModel ticketModel,
            @PathVariable(value = "id") Integer ticketIdParam) {
        
        Ticket ticket = ticketService.get(ticketIdParam);
        
        ticket.setStatus(Status.valueOf(ticketModel.getStatus()));

        ticketService.save(ticket);
        return new ResponseEntity<IdModel>(HttpStatus.OK);
    }
	
	private TicketModel entity2model(Ticket ticket) {

		TicketModel ticketModel = new TicketModel();

		Seance seance = seanceService.get(ticket.getSeance().getId());
		MovieTheater movieTheater = seance.getMovieTheater();
		Movie movie = seance.getMovie();

		ticketModel.setSeanceId(ticket.getSeance().getId());
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

		/*
		 * Seance seance = seanceService.get(ticket.getId()); MovieTheater
		 * movieTheater = seance.getMovieTheater(); String name = movieTheater.
		 */

		Seance seance = seanceService.get(ticketModel.getSeanceId());

		ticket.setSeance(seance);
		ticket.setCost(ticketModel.getCost());
		ticket.setRow(ticketModel.getRow());
		ticket.setPlace(ticketModel.getPlace());
		ticket.setStatus(Status.valueOf(ticketModel.getStatus()));

		return ticket;
	}
}
