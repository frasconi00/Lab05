package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private TextField txtInput;

    @FXML
    void doCalcolaAnagrammi(ActionEvent event) {
    	
    	txtCorretti.clear();
    	txtErrati.clear();
    	
    	String input = txtInput.getText();
    	
    	//Controllare che contenga solo lettere!
    	
    	//controllare che non sia vuoto il campo di imput
    	if(input==null || input.equals("")) {
    		txtCorretti.setText("Errore: inserisci una stringa");
    		return;
    	}
    	
    	if(!input.matches("[a-zA-Z]+")) {
    		txtCorretti.setText("Errore: inserisci solo caratteri alfabetici");
    		return;
    	}
    	
    	Map<String, Boolean> anagrammiFinal = this.model.calcolaAnagrammi(input);
    	
    	for(String s : anagrammiFinal.keySet()) {
    		if(anagrammiFinal.get(s)==true)
    			txtCorretti.appendText(s+"\n");
    		else
    			txtErrati.appendText(s+"\n");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtInput.clear();
    	txtCorretti.clear();
    	txtErrati.clear();
    }

    @FXML
    void initialize() {
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    void setModel(Model model) {
    	this.model = model;
    }

}
