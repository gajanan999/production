package com.billdiary.ui;

import java.io.IOException;

import java.io.PrintWriter;


import java.time.LocalDate;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.QuickProductDetails;
import com.billdiary.utility.Constants;
import com.billdiary.utility.FOPPdfDemo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
@Controller("QuickPayController")
public class QuickPayController  {
	@Autowired
	public LayoutController layoutController;
	
	@Autowired
	FOPPdfDemo pdfdemo;
	
	
	
	@FXML private TextField Name;
	@FXML private TextArea address;
	@FXML private TextField phoneno;
	@FXML private DatePicker todaydate;
	@FXML private TextField NameofProduct;
	@FXML private TextField Quantity;
	@FXML private TextField amount;
	@FXML private TextField total;
	@FXML private TextField Discount;
	@FXML private TextField TotalAfterDiscount;
	@FXML private TableView<QuickProductDetails> billTable;
	@FXML private TableColumn<QuickProductDetails, String>nameofProduct;
	@FXML private TableColumn<QuickProductDetails, Integer>quantity;
	@FXML private TableColumn<QuickProductDetails, Integer>amtperqauntity;
	@FXML private TableColumn<QuickProductDetails, Integer>totalamt;
	//@FXML private TableColumn<QuickProductDetails, Button>Delete;
	ObservableList<QuickProductDetails> data = FXCollections.observableArrayList();
	//Button Delete1=new Button("Delete");
	int amt=0;
	int t1=0;
	public void deleteButtonClicked()
	{
		System.out.println("inside DeleteButton Clicked");
		ObservableList< QuickProductDetails> a,b;
		a=billTable.getItems();
		b=billTable.getSelectionModel().getSelectedItems();	
		
		b.forEach(a::remove);
		//data.remove(a);
		//AddToTable();
		
		t1=0;
		for(int i=0;i<data.size();i++)
		{	
			float qauntity=data.get(i).getQuantity();
			float amount=data.get(i).getAmtperquantity();
			 t1+=(qauntity*amount);
		}
		String amt1=""+t1;
		total.setText(amt1);

		System.out.println(data.size());
		//calculateamt();
		
	}
	public QuickProductDetails obj()
	{	System.out.println("Obj Created");
		int total=totalamtperproduct();
		
	QuickProductDetails p=new QuickProductDetails(NameofProduct.getText(), Integer.parseInt(Quantity.getText()), Integer.parseInt(amount.getText()),total);
		return p;
	
	}
	
	public void calculateamt()
	{
		System.out.println("calculateamt");
		int t=totalamtperproduct();
		//amt+=Integer.parseInt(amount.getText());
		t1+=t;
		//amt+=t;
		String amt1=""+t1;
		total.setText(amt1);
		//return amt;
	}
	public int totalamtperproduct()
	{
		System.out.println("totalamtperproduct");
		int totalamt1=0;
		totalamt1+=(Integer.parseInt(Quantity.getText())*Integer.parseInt(amount.getText()));
		totalamt.setCellValueFactory(new PropertyValueFactory<QuickProductDetails,Integer>("totalamt"));
		System.out.println(" returning from totalamtperproduct");
		return totalamt1;
	}
	@FXML public void Calculate()
	{
		/*
		if(StringUtils.isEmpty(Discount.getText())) {
			System.out.println("Discount is empty");
		}
		*/
		if(!Discount.getText().isEmpty() )
		{
		int discount=Integer.parseInt(Discount.getText());
		System.out.println(discount);
		float DiscountedAmount=(discount*Float.parseFloat(total.getText()))/100;
		System.out.println(DiscountedAmount);
		float finalAmount=Float.parseFloat(total.getText())-DiscountedAmount;
		System.out.println(finalAmount);
		String amount=""+finalAmount;
		TotalAfterDiscount.setText(amount);
		}
		
	}
	@FXML private void AddToTable()
	{	
		//sCalendar today=Calendar.getInstance();
		//today.getDate();
	billTable.setEditable(true);
		//System.out.println(Integer.parseInt(phoneno.getText()));
		todaydate.setValue(LocalDate.now());
		
		QuickProductDetails p=obj();
		data.add(p);
		calculateamt();
		totalamtperproduct();
		
		nameofProduct.setCellValueFactory(new PropertyValueFactory<QuickProductDetails,String>("nameofproduct"));
		quantity.setCellValueFactory(new PropertyValueFactory<QuickProductDetails,Integer>("quantity"));
		amtperqauntity.setCellValueFactory(new PropertyValueFactory<QuickProductDetails,Integer>("amtperquantity"));
		//Delete.setCellValueFactory(new PropertyValueFactory<QuickProductDetails,Button>("Delete"));
		
		billTable.setItems(data);
		
	}
	
	
	@FXML private void generateBill() throws IOException, FOPException, TransformerException
		{
			generateXML();
			//FOPPdfDemo a=new FOPPdfDemo();
			pdfdemo.convertToPDF();
		 }
	public void generateXML()
	{

		try{
		    
			PrintWriter writer = new PrintWriter("C:\\bill\\Employees.xml", "UTF-8");
			writer.println("<?xml version='1.0'?><employees><companyname>ABC Inc.        </companyname>");
			writer.println("<NameOfClient>"+Name.getText()+"</NameOfClient>");
			writer.println("<date>"+todaydate.getValue()+"</date>");
			writer.println("<Address>"+address.getText()+"</Address>");
			writer.println("<phone>"+phoneno.getText()+"</phone>");
			int id=101;
			for(int i=0;i<data.size();i++)
			{
				writer.println("<employee><id>"+id+"</id>");
				writer.println("<name>"+data.get(i).getNameofproduct()+"</name>");
				writer.println("<Quantity>"+data.get(i).getQuantity()+"</Quantity>");
				writer.println("<amtperquantity>"+data.get(i).getAmtperquantity()+"</amtperquantity>");
				writer.println("<total>"+data.get(i).getTotalamt()+"</total>");
				writer.println("</employee>");
				id++;
			}
			writer.println("<total>"+total.getText()+"</total>");
			writer.println("<discount>"+Discount.getText()+"</discount>");
			writer.println("<Totalafterdiscount>"+TotalAfterDiscount.getText()+"</Totalafterdiscount>");
			writer.println("</employees>");
			//</employees>");
			
			
		    writer.close();
		    
		    
		} catch (IOException e) {
		   // do something
		}
		
		
	}

	@FXML public void AddPage()
	{
		System.out.println("inside add page");
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		AnchorPane addProduct=(AnchorPane) loader.load("/fxml/AddProducts.fxml");
		BorderPane root = new BorderPane();
		root.setCenter(addProduct);
		layoutController.loadWindow(root,"Add Products",565,134);
		
	}
	@FXML public void HidePage(Event event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
		AddToTable();
		
	}
	
	@FXML public void initialize(){
		//File file=new File("C:\\Users\\HP\\Desktop\\pay-bills logo.jpg");
		//Image image=new Image(file.toURI().toString());
		//Logo.setImage(image);
		Discount.textProperty().addListener(new ChangeListener<String>() {
			
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	
		        Calculate();
		    }
		});
	}
	
	
}
