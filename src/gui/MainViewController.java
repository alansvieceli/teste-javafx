package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	MenuItem menuItemVendedor;

	@FXML
	MenuItem menuItemDepartamento;

	@FXML
	MenuItem menuItemSobre;

	@FXML
	public void onMenuItemVendedorAcion() {
		System.out.println("onMenuItemVendedorAcion");
	}

	@FXML
	public void onMenuItemDepartamentoAcion() {
		System.out.println("onMenuItemDepartamentoAcion");
	}

	@FXML
	public void onMenuItemSobreAcion() {
		System.out.println("onMenuItemSobreAcion");
		LoadView("/gui/Sobre.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	private synchronized void LoadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newBox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar formulário", e.getMessage(), AlertType.ERROR);
		}

	}

}
