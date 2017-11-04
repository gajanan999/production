package com.billdiary.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class QuickProductDetails {

	private SimpleStringProperty nameofproduct;
	private SimpleIntegerProperty quantity;
	private SimpleIntegerProperty amtperquantity;
	private SimpleIntegerProperty totalamt;
	//private Button Delete;
	
	public QuickProductDetails(String nameofproduct, int quantity,
			int amtperquantity,int totalamt) {
		
		this.nameofproduct =new SimpleStringProperty(nameofproduct);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.amtperquantity = new SimpleIntegerProperty(amtperquantity);
		this.totalamt=new SimpleIntegerProperty(totalamt);
		//this.Delete=new Button("Delete");
	}
	public int getTotalamt() {
		return totalamt.get();
	}

	public String getNameofproduct() {
		return nameofproduct.get();
	}
	
	public void setNameofproduct(String productname) {
		//this.nameofproduct = nameofproduct;
		nameofproduct.set(productname);
	}

	public int getQuantity() {
		return quantity.get();
	}
	
	public int getAmtperquantity() {
		return amtperquantity.get();
	}
	
	
}
