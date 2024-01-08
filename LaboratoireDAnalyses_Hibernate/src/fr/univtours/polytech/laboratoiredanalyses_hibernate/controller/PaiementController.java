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
public class PaiementController {
	
	@FXML
	private Label labelMontant;
	
	@FXML
	private TextField textFieldNumeroCB;
	
	@FXML
	private TextField textFieldCVV;
	
	@FXML
	private ComboBox<String> comboBoxMoisExp;
	
	@FXML
	private ComboBox<String> comboBoxAnneeExp;
	
	@FXML
	private void initialize() {
		labelMontant.setText(Float.toString(ReservationController.getMontantAPayer()) + "€");
		ObservableList<String> listeMois = FXCollections.observableArrayList();
		for (int i = 1 ; i <= 12 ; i++) {
			listeMois.add(Integer.toString(i));
		}
		comboBoxMoisExp.setItems(listeMois);
		ObservableList<String> listeAnnees = FXCollections.observableArrayList();
		for (int i = LocalDate.now().getYear(); i <= 2100; i++) {
			listeAnnees.add(Integer.toString(i));
		}
		comboBoxAnneeExp.setItems(listeAnnees);
	}
	
	@FXML
	protected void handleButtonRetourAccueil() {
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
	
	@FXML
	protected void handleButtonPayer() {
		try {
			Long.valueOf(textFieldNumeroCB.getText());
			Integer.valueOf(textFieldCVV.getText());
			if (textFieldNumeroCB.getText().length() != 0 && textFieldCVV.getText().length() != 0 && comboBoxMoisExp.getValue() != null && comboBoxAnneeExp.getValue() != null) {
				LocalDate saisieExpCarteBancaire = LocalDate.of(Integer.valueOf(comboBoxAnneeExp.getValue()), Integer.valueOf(comboBoxMoisExp.getValue()), 1);
				if (saisieExpCarteBancaire.isAfter(LocalDate.now())) {
					Visite.reserverVisite(ReservationController.getIdVisite(), PageAccueilController.getNssPatient());
					Visite.payerVisite(ReservationController.getIdVisite(), Long.valueOf(textFieldNumeroCB.getText()), Integer.valueOf(textFieldCVV.getText()), saisieExpCarteBancaire);
					Alert alert = new Alert(AlertType.INFORMATION, "Paiement validé. Visite réservée. Merci, bonne journée.");
					alert.showAndWait();
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
					Alert alert = new Alert(AlertType.ERROR, "Carte bancaire expirée");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Un ou plusieurs champs sont vides");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "Un ou plusieurs champs sont incorrects");
			alert.showAndWait();
		}
	}
}
