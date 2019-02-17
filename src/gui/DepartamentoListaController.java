package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.utils.Alerts;
import gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	
	public void onBtInserirAction(ActionEvent event) {
		Departamento departamento = new Departamento();
		createDialogForm(departamento, "/gui/DepartamentoForm.fxml", Utils.currentStage(event));
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
	
	public void createDialogForm(Departamento departamento, String absoluteName, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane painel = loader.load();
			
			DepartamentoFormController controller = loader.getController();
			controller.setDepartamento(departamento);
			controller.setDepartamentoService(new DepartamentoService());
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com os dados do departamento");
			dialogStage.setScene(new Scene(painel));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar view", e.getMessage(), AlertType.ERROR);
		}
		
	}

}
