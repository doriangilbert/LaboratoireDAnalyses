package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;

import java.io.IOException;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		if (Patient.verificationIdentifiants(Long.parseLong(textFieldNSS.getText()), passwordFieldMDP.getText())) {
			// TODO : Ouvrir la page suivante
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Identifiants incorrects");
			alert.showAndWait();
		}
	}

}
