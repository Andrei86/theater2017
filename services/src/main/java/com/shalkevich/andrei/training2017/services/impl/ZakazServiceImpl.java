package com.shalkevich.andrei.training2017.services.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shalkevich.andrei.training2017.dao.impl.db.ISeanceDao;
import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.IZakazDao;
import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Movie;
import com.shalkevich.andrei.training2017.datamodel.Seance;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.Zakaz;
import com.shalkevich.andrei.training2017.services.IZakazService;

@Service
public class ZakazServiceImpl implements IZakazService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZakazServiceImpl.class);
	
	@Inject
	IZakazDao zakazDao;
	
	@Inject
	ITicketDao ticketDao;
	
	@Inject
	ISeanceDao seanceDao;
	
	@Override
	public Zakaz get(Integer id) {
		
		LOGGER.info("Get zakaz with {id} = " + id);
		
		return zakazDao.get(id);
	}

	@Override
	public void save(Zakaz zakaz) {
		
		if(zakaz.getId() == null)
		{
			LOGGER.info("Insert new Zakaz");
			zakazDao.insert(zakaz);
		}
		else
			LOGGER.info("Update new Zakaz");
			zakazDao.update(zakaz);
		
	}

	@Override
	public void saveMultiple(Zakaz... zakazArray) {
		
		LOGGER.info("Save new zakazy from array");
		
		for (Zakaz zakaz : zakazArray) {
			
			save(zakaz);
		}
		
	}

	@Override
	public void delete(Integer id) {
		
		LOGGER.info("Delete zakaz with id= "+id);
		
		zakazDao.delete(id);
		
	}

	@Override
	public List<Zakaz> searchBySeanceId(Integer seanceId) {
		
		LOGGER.info("Search new zakaz by id of seance");
		
		List<Zakaz> list = zakazDao.getBySeanceId(seanceId);
		
		return list;
	}

	@Override
	public List<Zakaz> searchByCustomerAndDates(Customer customer, Date dateFrom, Date dateTo) { // customer or customerId
		
		List<Zakaz> AllZakazByCustomerList = zakazDao.getByCustomerId(customer.getId());
		
		List<Zakaz> suitableZakazList = new ArrayList<>();
		
		Seance seance;
		Ticket ticket;
		Date date;
		
		if(dateFrom != null && dateTo != null)
		{
			for(Zakaz z: AllZakazByCustomerList)
			{
				ticket = ticketDao.get(z.getTicketId());
				seance = seanceDao.get(ticket.getSeanceId());
				date = seance.getDate();
				if(date.after(dateFrom) && date.before(dateTo))
					suitableZakazList.add(z);	
			}
			
			return suitableZakazList;
		}
		else
		return AllZakazByCustomerList;
	}

	@Override
	public BigDecimal getCostTicketSum(List<Zakaz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
