package com.shalkevich.andrei.training2017.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shalkevich.andrei.training2017.dao.impl.db.ICustomerDao;
import com.shalkevich.andrei.training2017.datamodel.Customer;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements ICustomerDao{

	// есть get(Id), getAll(), delete()
	
	final String INSERT_CUSTOMER = "INSERT INTO customer (login, password, first_name, last_name, e_mail, role) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
	final String UPDATE_CUSTOMER = "UPDATE customer SET login = ?, password = ?, first_name = ?, last_name = ?, e_mail = ?, "
				+ " role = ? WHERE id = ?";
	final String FIND_BY_LOGIN = "SELECT * FROM customer WHERE login = ?";
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Customer getByLogin(String login) {// login мб null, невалидное значение (ну это при апдейте)
		
		try
		{
			
		return jdbcTemplate.queryForObject(FIND_BY_LOGIN, new Object[]{login}, new BeanPropertyRowMapper<Customer>(Customer.class));
		
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	@Override
	public Customer insert(Customer entity) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); // для поддержки serial id
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER, new String[] { "id" });
	                ps.setString(1, entity.getLogin());
	                ps.setString(2, entity.getPassword());
	                ps.setString(3, entity.getFirstName());
	                ps.setString(4, entity.getLastName());
	                ps.setString(5, entity.geteMail());
	                ps.setString(6, entity.getRole().name());
	                return ps;
	            }
	        }, keyHolder);
		
		Number key = keyHolder.getKey();
		entity.setId(key.intValue());
		return entity;
	}

	@Override
	public void update(Customer entity) {
		
		 jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER);
	                ps.setString(1, entity.getLogin());
	                ps.setString(2, entity.getPassword());
	                ps.setString(3, entity.getFirstName());// пароль не обновляем
	                ps.setString(4, entity.getLastName());
	                ps.setString(5, entity.geteMail());
	                ps.setString(6, entity.getRole().name());
	                ps.setInt(7, entity.getId());
	                return ps;
	            }
	        });
		
	}

}
