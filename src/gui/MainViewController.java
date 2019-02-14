package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartamentoService;

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
		LoadView("/gui/DepartamentoLista.fxml", (DepartamentoListaController controller) -> {
			controller.setDepartamentoService(new DepartamentoService());
			controller.updateTableView();			
		});
	}

	@FXML
	public void onMenuItemSobreAcion() {
		LoadView("/gui/Sobre.fxml", x -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	private synchronized <T> void LoadView(String absoluteName, Consumer<T> initializiongAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newBox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newBox.getChildren());
			
			T controller = loader.getController();
			initializiongAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar formulário", e.getMessage(), AlertType.ERROR);
		}

	}

}
