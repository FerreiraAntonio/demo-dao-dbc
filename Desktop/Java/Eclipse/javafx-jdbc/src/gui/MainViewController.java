 package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
<<<<<<< HEAD
import java.util.function.Consumer;
=======
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import model.entities.Department;
<<<<<<< HEAD
import model.services.DepartmentService;
=======
import model.services.DepartmentServide;
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSeller() {
		System.out.println("funfa seller");
	}
	
	public void onMenuItemDepartment() {
<<<<<<< HEAD
		//implementado um lambida na chamada do metodo
		loadViewAbout("/gui/DepartmentList.fxml", (DepartmentListController controller)->{controller.setDepartmentServide
			(new DepartmentService());
		controller.upDateView();
		});
	}
	
	public void onMenuItemAbout() {
		//implementado um lambida sem retorno apenas para a chamada da janela about
		loadViewAbout("/gui/About.fxml", x ->{});
=======
		//loadViewAbout("/gui/DepartmentList.fxml");
		loadViewAbout2("/gui/DepartmentList.fxml");
	}
	
	public void onMenuItemAbout() {

		loadViewAbout("/gui/About.fxml");
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
	 
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	} 
<<<<<<< HEAD
	
	//Nesse metodo implementanos uma funçao com a classe generica Consumer
	private synchronized <T> void loadViewAbout(String absoluteName, Consumer <T> inicializar) {
=======
	private synchronized void loadViewAbout(String absoluteName) {
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
		
		try { 
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox vbox=  fxmloader.load();
			Scene mainScene= Main.getMainScene();
			VBox mainVbox= (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			Node mainmenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainmenu);
			mainVbox.getChildren().addAll(vbox.getChildren());
<<<<<<< HEAD
			
			//inicializar  o fxloader
			T controller =fxmloader.getController();
			inicializar.accept(controller);
			
			
=======
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
		} catch (IOException e) {
			
			Alerts.showAlert("IO Exception",null, e.getMessage(), AlertType.ERROR);
		}
		
		
		
 
	}
	
	
private synchronized void loadViewAbout2(String absoluteName) {
<<<<<<< HEAD

		
 

}}
=======
		
		try { 
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox vbox=  fxmloader.load();
			Scene mainScene= Main.getMainScene();
			VBox mainVbox= (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			Node mainmenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainmenu);
			mainVbox.getChildren().addAll(vbox.getChildren());
			
			DepartmentListController controller= fxmloader.getController();
			controller.setDepartmentServide(new DepartmentServide());
			controller.upDateView();
			
			
		} catch (IOException e) {
			
			Alerts.showAlert("IO Exception",null, e.getMessage(), AlertType.ERROR);
		}}
		
 

}
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
