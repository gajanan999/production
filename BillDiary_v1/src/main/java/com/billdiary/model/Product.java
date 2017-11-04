package com.billdiary.model;

import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Component
public class Product {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleFloatProperty price;
	public Product()
	{
		
	}
	public int getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public Product(int id,String name,float price)
	{

		this.id=new SimpleIntegerProperty(id);
		this.name=new SimpleStringProperty(name);
		this.price=new SimpleFloatProperty(price);
	}

}
