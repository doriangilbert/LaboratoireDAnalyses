package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;

import java.io.IOException;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
		try {
			if (Patient.verificationIdentifiants(Long.parseLong(textFieldNSS.getText()), passwordFieldMDP.getText())) {
				try
				{
					Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/laboratoiredanalyses_hibernate/view/ReservationView.fxml"));

					Scene scene = new Scene(Main.root, 640, 400);

					Main.primaryStage.setScene(scene);
					Main.primaryStage.show();

				} catch (Exception error) {
					error.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Identifiants incorrects");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "Numéro de sécurité sociale incorrect");
			alert.showAndWait();
		}
	}

}
