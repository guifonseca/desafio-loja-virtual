package br.com.lojavirtual.model;

import java.util.HashSet;
import java.util.Set;

public class Product {

	private long id;
	private String name;
	private double price;
	private int stock;

	private Set<Sales> sales;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
	
	public void addSales(Sales sale) {
		if(this.sales == null)
			this.sales = new HashSet<Sales>();
		
		this.sales.add(sale);
	}

	public void removeSale(Sales sale) {
		this.sales.remove(sale);
	}

}
