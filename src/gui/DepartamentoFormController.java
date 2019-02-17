package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.utils.Alerts;
import gui.utils.Constraints;
import gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Departamento;
import model.exceptions.ValidationException;
import model.services.DepartamentoService;

public class DepartamentoFormController implements Initializable {

	private Departamento entity;
	private DepartamentoService service;
	
	private List<DataChangeListener> datachangelisteners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private Label lblErro;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
	}

	public void setDepartamentoService(DepartamentoService service) {
		this.service = service;
	}

	public void setDepartamento(Departamento entity) {
		this.entity = entity;
	}
	
	public void addDataChangeListener(DataChangeListener listner) {
		datachangelisteners.add(listner);
	}
	
	@FXML
	private void onBtSalvarAction(ActionEvent event) {
		
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		try {
			service.saveOrUpdate(getFormData());
			notifyDataChangeListners();
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar objeto", null, e.getMessage(), AlertType.ERROR);
		} catch (ValidationException e ) {
			setErrorMessages(e.getErros());
		}
		

	}


	@FXML
	private void onBCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void updateFormData() {

		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}

		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
	}
	
	private Departamento getFormData() {
		Departamento obj = new Departamento();
		
		ValidationException erro = new ValidationException("Erros de Valida��o: ");
				
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			erro.addErro("nome", "Nome n�o pode ser em branco...");
		}
		obj.setNome(txtNome.getText());
		
		if (erro.getErros().size() > 0) {
			throw erro;
		}
		
		return obj;
	}

	private void notifyDataChangeListners() {
		for (DataChangeListener listiner: datachangelisteners) {
			listiner.onDataChanged();
		}
		
	}

	private void setErrorMessages(Map<String, String> erros) {
		Set<String> keys = erros.keySet();
		
		if (keys.contains("nome")) {
			lblErro.setText(erros.get("nome"));
		}
		
	}
}
