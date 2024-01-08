package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;

import java.time.LocalDate;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Visite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Dorian GILBERT
 *
 */

/**
 * Classe représentant le controleur de la page de paiement
 */
public class PaiementController {
	
	/**
	 * Attribut privé représentant le label du montant à payer
	 */
	@FXML
	private Label labelMontant;
	
	/**
	 * Attribut privé représentant le champ de texte du numéro de carte bancaire
     */
	@FXML
	private TextField textFieldNumeroCB;
	
	/**
	 * Attribut privé représentant le champ de texte du CVV
	 */
	@FXML
	private TextField textFieldCVV;
	
	/**
	 * Attribut privé représentant la combo box du mois d'expiration de la carte bancaire
	 */
	@FXML
	private ComboBox<String> comboBoxMoisExp;
	
	/**
	 * Attribut privé représentant la combo box de l'année d'expiration de la carte bancaire
	 */
	@FXML
	private ComboBox<String> comboBoxAnneeExp;
	
	/**
	 * Méthode d'initialisation de la page de paiement
	 */
	@FXML
	private void initialize() {
		// Affichage du montant à payer
		labelMontant.setText(Float.toString(ReservationController.getMontantAPayer()) + "€");
		// Création d'une liste des mois à mettre dans la combo box
		ObservableList<String> listeMois = FXCollections.observableArrayList();
		// Ajout des mois à la liste
		for (int i = 1 ; i <= 12 ; i++) {
			listeMois.add(Integer.toString(i));
		}
		// Ajout de la liste des mois à la combo box
		comboBoxMoisExp.setItems(listeMois);
		// Création d'une liste des années à mettre dans la combo box
		ObservableList<String> listeAnnees = FXCollections.observableArrayList();
		// Ajout des années à la liste
		for (int i = LocalDate.now().getYear(); i <= 2100; i++) {
			listeAnnees.add(Integer.toString(i));
		}
		// Ajout de la liste des années à la combo box
		comboBoxAnneeExp.setItems(listeAnnees);
	}
	
	/**
	 * Méthode permettant de gérer un clic sur le bouton de retour à l'accueil
	 */
	@FXML
	protected void handleButtonRetourAccueil() {
		// Affichage de la page d'accueil
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/laboratoiredanalyses_hibernate/view/PageAccueilView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();

		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de gérer un clic sur le bouton de paiement
	 */
	@FXML
	protected void handleButtonPayer() {
		try {
			// Vérification de la validité des valeurs saisies
			Long.valueOf(textFieldNumeroCB.getText());
			Integer.valueOf(textFieldCVV.getText());
			// Vérification du remplissage des champs
			if (textFieldNumeroCB.getText().length() != 0 && textFieldCVV.getText().length() != 0 && comboBoxMoisExp.getValue() != null && comboBoxAnneeExp.getValue() != null) {
				// Création d'une date d'expiration de carte bancaire
				LocalDate saisieExpCarteBancaire = LocalDate.of(Integer.valueOf(comboBoxAnneeExp.getValue()), Integer.valueOf(comboBoxMoisExp.getValue()), 1);
				// Vérification de la date d'expiration de la carte bancaire
				if (saisieExpCarteBancaire.isAfter(LocalDate.now())) {
					// Réservation de la visite
					Visite.reserverVisite(ReservationController.getIdVisite(), PageAccueilController.getNssPatient());
					// Paiement de la visite
					Visite.payerVisite(ReservationController.getIdVisite(), Long.valueOf(textFieldNumeroCB.getText()), Integer.valueOf(textFieldCVV.getText()), saisieExpCarteBancaire);
					// Affichage d'un message de confirmation
					Alert alert = new Alert(AlertType.INFORMATION, "Paiement validé. Visite réservée. Merci, bonne journée.");
					alert.showAndWait();
					// Affichage de la page d'accueil
					try
					{
						Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/laboratoiredanalyses_hibernate/view/PageAccueilView.fxml"));

						Scene scene = new Scene(Main.root, 640, 400);

						Main.primaryStage.setScene(scene);
						Main.primaryStage.show();

					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					// Affichage d'un message d'erreur
					Alert alert = new Alert(AlertType.ERROR, "Carte bancaire expirée");
					alert.showAndWait();
				}
			} else {
				// Affichage d'un message d'erreur
				Alert alert = new Alert(AlertType.ERROR, "Un ou plusieurs champs sont vides");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			// Affichage d'un message d'erreur
			Alert alert = new Alert(AlertType.ERROR, "Un ou plusieurs champs sont incorrects");
			alert.showAndWait();
		}
	}
}
