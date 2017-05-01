package com.shalkevich.andrei.training2017.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.datamodel.Zakaz;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;


public interface IZakazService extends IGenericService<Zakaz> {
	
	/*List<Zakaz> searchBySeanceId(Integer seanceId); // нужно искать билеты заказанные а не сами заказы, ведь так удобней смотреть
	
	List<Zakaz> searchByCustomerAndDates(Customer customer, Date dateFrom, Date dateTo);*/
	
	List<TicketWithAllData> searchBySeanceId(Integer seanceId); // нужно искать билеты заказанные а не сами заказы, ведь так удобней смотреть
	
	List<TicketWithAllData> searchByCustomerAndDates(Customer customer, Date dateFrom, Date dateTo);*/
	
	BigDecimal getCostTicketSum(List<Zakaz> list); // уточнить!
	
}
