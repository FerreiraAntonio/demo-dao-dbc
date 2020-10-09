package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
<<<<<<< HEAD
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service;
=======
import model.services.DepartmentServide;

public class DepartmentListController implements Initializable{
	
	private DepartmentServide servide;
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d

	@FXML
	private TableView<Department>tableViewDepartment;
	
	@FXML
	private TableColumn<Department,Integer> tableColumId;
	
	@FXML 
	private TableColumn<Department,String> tableColumName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> oblist;
	
	
<<<<<<< HEAD
	public void setDepartmentServide(DepartmentService service) {
		
	this.service=service;
=======
	public void setDepartmentServide(DepartmentServide service) {
		
	this.servide=service;
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
	}
	
	@FXML
	public void onBtNewAction() {
		System.out.println("OnBT");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
		
	}
	
	private void initializeNode() {
		tableColumId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
<<<<<<< HEAD
		//Método de fazer a janela se edequar ao Conteiner em qq tamanho
=======
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
		Stage stage= (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
		
	}
	public void upDateView() {
<<<<<<< HEAD
		if(service==null) {
			throw new IllegalStateException("deu ruim, servide null");
		}
		List<Department>list=service.findAll();
=======
		if(servide==null) {
			throw new IllegalStateException("deu ruim, servide null");
		}
		List<Department>list=servide.findAll();
>>>>>>> f3acff1c426850a3beff7db5b4cbc644a930ea7d
		oblist=FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(oblist);
	}
}
