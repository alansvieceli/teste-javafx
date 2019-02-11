package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{
	
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
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
