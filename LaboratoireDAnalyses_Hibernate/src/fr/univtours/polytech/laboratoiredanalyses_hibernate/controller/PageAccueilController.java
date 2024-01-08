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

/**
 * Classe représentant le controleur de la page d'accueil
 */
public class PageAccueilController {

	/**
	 * Attribut privé représentant le champ de texte du numéro de sécurité sociale
     */
	@FXML
	private TextField textFieldNSS;

	/**
	 * Attribut privé représentant le champ de texte du mot de passe
	 */
	@FXML
	private PasswordField passwordFieldMDP;
	
	/**
	 * Attribut privé représentant le numéro de sécurité sociale du patient
	 */
	private static long nssPatient;

	/**
	 * Accesseur en lecture du numéro de sécurité sociale du patient
	 * @return nssPatient Le numéro de sécurité sociale du patient
	 */
	public static long getNssPatient() {
		return nssPatient;
	}

	/**
	 * Accesseur en écriture du numéro de sécurité sociale du patient
	 * @param nssPatient Nouvelle valeur du numéro de sécurité sociale du patient
	 */
	public static void setNssPatient(long nssPatient) {
		PageAccueilController.nssPatient = nssPatient;
	}

	/**
	 * Méthode permettant de gérer un clic sur le bouton de connexion
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleButtonSeConnecter(ActionEvent event) throws IOException {
		try {
			// Vérification des identifiants
			if (Patient.verificationIdentifiants(Long.parseLong(textFieldNSS.getText()), passwordFieldMDP.getText())) {
				// Récupération du numéro de sécurité sociale du patient
				nssPatient = Long.parseLong(textFieldNSS.getText());
				// Affichage de la page de réservation
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
				// Affichage d'un message d'erreur
				Alert alert = new Alert(AlertType.ERROR, "Identifiants incorrects");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			// Affichage d'un message d'erreur
			Alert alert = new Alert(AlertType.ERROR, "Numéro de sécurité sociale incorrect");
			alert.showAndWait();
		}
	}

}
