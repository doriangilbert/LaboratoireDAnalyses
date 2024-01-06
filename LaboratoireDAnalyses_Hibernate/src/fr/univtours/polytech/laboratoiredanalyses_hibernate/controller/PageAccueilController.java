package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;

import java.io.IOException;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Dorian GILBERT
 *
 */
public class PageAccueilController {

	@FXML
	private TextField textFieldNSS;

	@FXML
	private PasswordField passwordFieldMDP;

	@FXML
	protected void handleButtonSeConnecter(ActionEvent event) throws IOException {
		System.out.println("NSS : " + textFieldNSS.getText());
		System.out.println("MDP : " + passwordFieldMDP.getText());
		System.out.println(Patient.verificationIdentifiants(Long.parseLong(textFieldNSS.getText()), passwordFieldMDP.getText()));
	}

}
