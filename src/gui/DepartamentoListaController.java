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
import model.entities.Departamento;
import model.services.DepartamentoService;

public class DepartamentoListaController implements Initializable {
	
	private DepartamentoService service;
	
	@FXML
	private TableView<Departamento> tableViewDepartamento;
	
	@FXML
	private TableColumn<Departamento, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departamento, String> tableColumnNome;
	
	@FXML 
	Button btInserir;
	
	private ObservableList<Departamento> obsList;
	
	public void onBtInserirAction() {
		System.out.println("OK");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		InicializeNodes();		
	}
	
	//Injeç~;ao de dependencia, principio SOLID
	public void setDepartamentoService(DepartamentoService service) {
		this.service = service;		
	}

	private void InicializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		//table view acompanhar o tamanho da janela
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está null");
		}
		
		List<Departamento> lista = service.findAll();
		
		obsList = FXCollections.observableArrayList(lista);
		
		tableViewDepartamento.setItems(obsList);
	}

}
