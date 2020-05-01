package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> comboNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnCase;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCase(ActionEvent event) {
    	txtHours.clear();
    	txtResult.clear();
    	txtYears.clear();
    	
    	int ore=0;
    	int anni=0;
    	
    	try {
    		ore=Integer.parseInt(txtHours.getText());
    		anni=Integer.parseInt(txtYears.getText());
    	}catch(NumberFormatException nfe){
    		txtResult.appendText("Solo numeri interi lurido bastardo\n");
    	}
    	
    	
    	
    	

    }

    @FXML
    void initialize() {
        assert comboNerc != null : "fx:id=\"comboNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCase != null : "fx:id=\"btnCase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model=model;
		comboNerc.getItems().addAll(model.getNercList());
		txtResult.setEditable(true);
		
		
		
	}
    
}
