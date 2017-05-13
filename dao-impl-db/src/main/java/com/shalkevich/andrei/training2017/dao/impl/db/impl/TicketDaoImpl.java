package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketMapper;
import com.shalkevich.andrei.training2017.datamodel.Ticket;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements ITicketDao{

	@Inject
	JdbcTemplate jdbcTemplate;
	
	final String FIND_FULL_TICKET_BY_ID = " SELECT t.id as ticket_id, t.seance_id, s.movietheater_id, s.movie_id, s.date, s.time, mt.name, mt.city, mt.address, mt.is_active, " 
			+ " m.title, m.age_bracket, m.duration, m.description, t.cost, t.row, t.place, t.status FROM ticket t "
			+ " INNER JOIN seance s ON t.seance_id = s.id "
			+ " INNER JOIN movietheater mt ON mt.id = s.movietheater_id "
			+ " INNER JOIN movie m ON m.id = s.movie_id WHERE mt.is_active = true AND t.id = ? "; // в активном кинотеатре
	
	final String FIND_ALL_FULL_TICKET = " SELECT t.id as ticket_id, t.seance_id, s.movietheater_id, s.movie_id, s.date, s.time, mt.name, mt.city, mt.address, mt.is_active, " 
			+ " m.title, m.age_bracket, m.duration, m.description, t.cost, t.row, t.place, t.status FROM ticket t "
			+ " INNER JOIN seance s ON t.seance_id = s.id "
			+ " INNER JOIN movietheater mt ON mt.id = s.movietheater_id "
			+ " INNER JOIN movie m ON m.id = s.movie_id WHERE mt.is_active = true ";
	
	final String INSERT_TICKET = "INSERT INTO ticket (seance_id, cost, row, place, status) values(?, ?, ?, ?, ?)";

	final String UPDATE_TICKET = "UPDATE ticket set seance_id = ?, cost = ?, row = ?, place = ?, status = ? WHERE id = ?";
	
	@Override
	public Ticket get(Integer ticketId) {

		try
		{
			return jdbcTemplate.queryForObject(FIND_FULL_TICKET_BY_ID, new Object[]{ticketId}, new TicketMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
		
	}
	
/*	@Override
	public TicketWithAllData getByTicketId(Integer ticketId) {
		
		return jdbcTemplate.queryForObject(" select * from ticket t join seance s on t.seance_id = s.id "
				+ "join movietheater m_t on m_t.id = s.movietheater_id "
				+ "join movie m on s.movie_id = m.id where t.id = " + ticketId, new TicketWithAllDataMapper());
		
	}

	@Override
	public List<TicketWithAllData> getBySeanceAndStatus(Integer seanceId, Status status) { // рефакторить, а то плодим кучу strings
		
		String sqlBySeanceAndStatus = "select * from ticket t join seance s on t.seance_id = s.id "
				+ "join movietheater m_t on m_t.id = s.movietheater_id "
				+ "join movie m on s.movie_id = m.id where t.seance_id = " + seanceId + " AND t.status = '" + status.name() + "'";
		
		return jdbcTemplate.query(sqlBySeanceAndStatus, new TicketWithAllDataMapper());
	}

	@Override
	public List<TicketWithAllData> getBySeance(Integer seanceId) {
		
		String sqlBySeance = "select * from ticket t join seance s on t.seance_id = s.id "
				+ "join movietheater m_t on m_t.id = s.movietheater_id "
				+ "join movie m on s.movie_id = m.id where t.seance_id = " + seanceId;
		
		return jdbcTemplate.query(sqlBySeance, new TicketWithAllDataMapper());
	}*/

	/*@Override
	public List<TicketWithAllData> search(TicketWithAllDataFilter filter) { // нужно бросить исключение если фильтр равен null
		 String sql = "select * from ticket t join seance s on t.seance_id = s.id left join customer c "
				+ "on t.customer_id = c.id join movietheater m_t on m_t.id = s.movietheater_id "
				+ "join movie m on s.movie_id = m.id ";// пока говнокод
		
		t.id, t.cost, t.row, t.place, t.status, m_t.name, m.title, s.date, s.time
		String and = " and ";
		
		String where = " where ";
		
		if(filter.getSeanceId()!=null)
		sql += where + filter.seanceFilterResult();
		
		if(filter.getCustomerId()!=null)
			sql += ((sql.contains(where)) ? and : where ) + filter.customerFilterResult();
		
		if(filter.getDateFrom()!=null)
			sql += ((sql.contains(where)) ? and : where ) + filter.dateFromFilterResult();
		
		if(filter.getDateTo()!=null)
			sql += ((sql.contains(where)) ? and : where ) + filter.dateToFilterResult();
		
		System.out.println(sql);
		
		List<TicketWithAllData> list = jdbcTemplate.query(sql, new TicketWithAllDataMapper());
		
		return list;
	}*/

	@Override
	public List<Ticket> searchByFilter(TicketFilter filter) {
		
		String sqlForTicketFilter = FIND_ALL_FULL_TICKET + filter.seanceIdFilterResult() + filter.statusFilterResult();
		try
		{
			return jdbcTemplate.query(sqlForTicketFilter, new TicketMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	@Override
	public Ticket insert(Ticket entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_TICKET, new String[] { "id" });
	                ps.setObject(1, entity.getSeance().getId());
	                ps.setBigDecimal(2, entity.getCost());
	                ps.setInt(3, entity.getRow());
	                ps.setInt(4, entity.getPlace());
	                ps.setString(5, entity.getStatus().name());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		
		
		return entity;
	}

	@Override
	public void update(Ticket entity) {
	
	 jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_TICKET);
                ps.setObject(1, entity.getSeance().getId());
                ps.setBigDecimal(2, entity.getCost());
                ps.setInt(3, entity.getRow());
                ps.setInt(4, entity.getPlace());
                ps.setString(5, entity.getStatus().name());
                ps.setInt(6, entity.getId());
                return ps;
            }
        });
		
	}

	/*@Override
	public List<TicketWithAllData> getByCustomerIdWithInterval(Integer id, Date date1, Date date2) {
		
		List<TicketWithAllData> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
		 + "join movietheater m_t on s.movietheater_id = m_t.id join movie m on s.movie_id = m.id "
		 + "join customer c on t.customer_id = c.id " 
		 + "where c.id = ? and t.purchase_date >= ? and t.purchase_date <= ?" , new Object[] {id, date1, date2} ,
				new TicketWithAllDataMapper());
		
		return list;
		
	}

	@Override
	public List<TicketWithAllData> getAll(Integer seanceId) {
		List<TicketWithAllData> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
		+ "join movie_theater m_t on s.movie_theater_id = m_t.id join movie m on s.movie_id = m.id "
		+ "join customer c on t.customer_id = c.id " 
		+ "where s.id = ?" , new Object[] {seanceId} ,
		new TicketWithAllDataMapper());
				
		return list;
	}
	
	@Override
	public List<TicketWithAllData> getBySeanceAndStatus(Integer seanceId, Status status) {
		List<TicketWithAllData> list = jdbcTemplate.query("select * from ticket t join seance s on t.seance_id = s.id "
				+ "join movietheater m_t on s.movietheater_id = m_t.id join movie m on s.movie_id = m.id "
				+ "join customer c on t.customer_id = c.id " 
				+ "where s.id = ? and t.status = ?" , new Object[] {seanceId, status} ,
				new TicketWithAllDataMapper());
						
				return list;
	}
	
	@Override
	public TicketCostSum getTicketCostSum(Integer seanceId, String status) {
		
		TicketCostSum instance = jdbcTemplate.queryForObject("select SUM(cost) from ticket "
				+ "where seance_id = ? and status = ?", new Object[]{seanceId, status},
				new TicketCostSumMapper());
		
		return instance;
	}
	
	@Override
	public TicketCostSum getTicketCostSumAll(Integer seanceId) {
		TicketCostSum instance = jdbcTemplate.queryForObject("select SUM(cost) from ticket "
				+ "where seance_id = ?", new Object[]{seanceId},
				new TicketCostSumMapper());
		
		return instance;
	}*/

}
