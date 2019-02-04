package com.lojavirtual.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "SALES")
public class Sales implements Serializable {

	private static final long serialVersionUID = -2701297777144137424L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;
	private String error;
	private String success;
	@Column(unique = true)
	private long idTransaction;
	private Date dateTransaction;
	private int amount;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sales )) return false;
        return id == ((Sales) o).id;
    }

	@Override
	public String toString() {
		return "Sales [id=" + id + ", product=" + product + ", error=" + error + ", success=" + success
				+ ", idTransaction=" + idTransaction + ", dateTransaction=" + dateTransaction + ", amount=" + amount
				+ "]";
	}
	
}
