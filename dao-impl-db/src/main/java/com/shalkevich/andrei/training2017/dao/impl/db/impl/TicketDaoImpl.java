package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ITicketDao;
import com.shalkevich.andrei.training2017.dao.impl.db.filter.TicketWithAllDataFilter;
import com.shalkevich.andrei.training2017.dao.impl.db.mapper.TicketWithAllDataMapper;
import com.shalkevich.andrei.training2017.datamodel.Ticket;
import com.shalkevich.andrei.training2017.datamodel.customData.Status;
import com.shalkevich.andrei.training2017.datamodel.customData.TicketWithAllData;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements ITicketDao{

	@Inject
	JdbcTemplate jdbcTemplate;
	
	/*НАДО ВСЕ-ТАКИ ФИЛЬТР НАПИСАТЬ НА TicketWithAllData ПОТОМУ ЧТО МНОГО ГОВНОКОДА!!*/
	
	@Override
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
		
		System.out.println(sqlBySeanceAndStatus);
		
		return jdbcTemplate.query(sqlBySeanceAndStatus, new TicketWithAllDataMapper());
	}

	@Override
	public List<TicketWithAllData> getBySeance(Integer seanceId) {
		
		String sqlBySeance = "select * from ticket t join seance s on t.seance_id = s.id "
				+ "join movietheater m_t on m_t.id = s.movietheater_id "
				+ "join movie m on s.movie_id = m.id where t.seance_id = " + seanceId;
		
		return jdbcTemplate.query(sqlBySeance, new TicketWithAllDataMapper());
	}

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
	public void deleteAll(Integer seanceId) {
		
		jdbcTemplate.update("delete from ticket where seance_id=" + seanceId);
		
	}

	@Override
	public Ticket insert(Ticket entity) {
			final String INSERT_SQL = "insert into ticket (seance_id, cost, row, place, status)"
					+ " values(?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
	                ps.setInt(1, entity.getSeanceId());
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
		
		final String UPDATE_SQL = "update ticket set seance_id = ?, cost = ?, row = ?, place = ?, "
				+ "status = ? where id =" + entity.getId();

	
	 jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
                ps.setInt(1, entity.getSeanceId());
                ps.setBigDecimal(2, entity.getCost());
                ps.setInt(3, entity.getRow());
                ps.setInt(4, entity.getPlace());
                ps.setString(5, entity.getStatus().name());
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
