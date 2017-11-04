package com.billdiary.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
@Controller("HomeController")
public class HomeController {
	@FXML public StackPane homepage;
	@FXML public  BorderPane borderpane;
	@FXML public AnchorPane mainView;
	
	
	
	//ajcha kuytana
	@FXML public StackPane centerStackPane;
	@FXML public BorderPane centerBorderPane;
	@FXML public void showProduct()
	{
		centerBorderPane.setCenter(null);
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		StackPane manageProduct2=(StackPane)loader.load("/fxml/manageProduct2.fxml");
		centerBorderPane.setCenter(manageProduct2);
		
		
	}
	@FXML public void initialize(){
		
		
	}
	
	
	
	
	
	
	
	//@FXML public Text MainTitle;
	public  StackPane MainStage= new StackPane();
	
	AnchorPane mainInnerWindow;
	
	
	
	@Autowired
	public HomeController homeController;
	
	@Autowired
	public LayoutController layoutController;
	
	public  StackPane getRoot() {
		return homepage;
	}
	@FXML private void manageProducts(ActionEvent event)
	{
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		 mainInnerWindow=(AnchorPane) loader.load(URLS.MANAGE_PRODUCT_PAGE);
		//MainTitle.setText("Manage Products");
		if(setMainView())
		{
			System.out.println("Manage Products window loaded successfully");
		}else {
			System.out.println("while loading Manage Products window some error has been occured ");
		}	
	}
	
	@FXML private void manageCustomers(ActionEvent event)
	{
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		 mainInnerWindow=(AnchorPane) loader.load(URLS.MANAGE_CUSTOMER_PAGE);
	  //  MainTitle.setText("Manage Customers");
		if(setMainView())
		{
			System.out.println("Manage Customer window loaded successfully");
		}else {
			System.out.println("while loading Manage Customer window some error has been occured ");
		}
	}
	
	public void createMainStage(StackPane mainView)
	{
		layoutController.loadWindow(MainStage, Constants.APPLICATION_TITLE,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
	}
	
	private boolean setMainView()
	{
		boolean windowShow=false;
		try
		{
		mainView.getChildren().clear();
		
		mainInnerWindow.setLayoutX(mainView.getLayoutX());
		mainInnerWindow.setLayoutY(mainView.getLayoutY());
		
		System.out.println("new view"+mainInnerWindow.getWidth()+ " "+mainInnerWindow.getHeight());
		System.out.println("mainview"+mainView.getWidth()+ " "+mainView.getHeight());
		
		mainInnerWindow.setMaxHeight(mainView.getHeight());
		mainInnerWindow.setMaxWidth(mainView.getWidth());
		
		mainInnerWindow.resize(mainView.getWidth(), mainView.getHeight());
		System.out.println("new view"+mainInnerWindow.getWidth()+ " "+mainInnerWindow.getHeight()+" :"+mainInnerWindow.computeAreaInScreen());
		System.out.println("mainview:"+mainView.computeAreaInScreen());
		mainInnerWindow.setLayoutX(mainView.getLayoutX());
		mainInnerWindow.setLayoutY(mainView.getLayoutY());
		
		
		AnchorPane.setTopAnchor(mainInnerWindow, 0.0);
        AnchorPane.setRightAnchor(mainInnerWindow, 0.0);
        AnchorPane.setLeftAnchor(mainInnerWindow, 0.0);
        AnchorPane.setBottomAnchor(mainInnerWindow, 0.0);
		
		mainView.getChildren().add(mainInnerWindow);
		mainInnerWindow.resize(mainView.getWidth(), mainView.getHeight());
		mainView.autosize();
		
		
		
		windowShow=true;
		}catch(Exception e)
		{
			windowShow=false;
			System.out.println(e.getMessage());
		}
		return windowShow;
		
	}
	
}
