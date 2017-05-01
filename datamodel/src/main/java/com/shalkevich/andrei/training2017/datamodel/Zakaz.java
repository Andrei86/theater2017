package com.shalkevich.andrei.training2017.datamodel;

import java.sql.Timestamp;

public class Zakaz {
	
	private Integer id;
	
	private Integer customerId;
	
	private Integer ticketId;
	
	private Timestamp paymentDate ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Zakaz)) return false;
		
		Zakaz z = (Zakaz) obj;
		
		return id.equals(z.id) && customerId.equals(z.customerId) && ticketId.equals(z.ticketId) && 
				paymentDate.equals(z.paymentDate);
	}

	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + customerId.hashCode();
        code = 31 * code + ticketId.hashCode();
        code = 31 * code + paymentDate.hashCode();
        
        return code;
	}

	@Override
	public String toString() {
		
		return  "Zakaz [id= " + id + "]" + " customer " + customerId + " ticket " + ticketId +
				" payment date " + paymentDate;
	}
	
	

}
