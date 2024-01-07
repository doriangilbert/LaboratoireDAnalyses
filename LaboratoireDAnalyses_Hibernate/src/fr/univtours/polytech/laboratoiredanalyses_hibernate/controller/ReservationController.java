package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Analyse;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.DatabaseLink;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Visite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * @author Dorian GILBERT
 *
 */
public class ReservationController {
	
	@FXML
	private ComboBox<String> comboBoxAnalyse;
	
	@FXML
	private Label labelDate;
	
	@FXML
	private Label labelHeure;
	
	@FXML
	private Button buttonReserver;
	
	private static float montantAPayer;

	public static float getMontantAPayer() {
		return montantAPayer;
	}

	public static void setMontantAPayer(float montantAPayer) {
		ReservationController.montantAPayer = montantAPayer;
	}

	@FXML
	private void initialize() {
		String strRequete = "SELECT a FROM Analyse a";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Analyse> listeResultat = requete.getResultList();
		DatabaseLink.close();
		ObservableList<String> listeAnalyses = FXCollections.observableArrayList();
		for (Analyse analyse : listeResultat) {
			listeAnalyses.add(analyse.getIdAnalyse() + " - " + analyse.getNomAnalyse() + " - " + analyse.getPrixAnalyse() + "€");
		}
		comboBoxAnalyse.setItems(listeAnalyses);
	}
	
	@FXML
	protected void handleComboBoxAnalyse(ActionEvent event) throws IOException {
		int selectIdAnalyse = Integer.parseInt(comboBoxAnalyse.getValue().split(" - ")[0]);
		String strRequete = "SELECT v FROM Visite v WHERE dateHeureVisite >= NOW() AND nssPatient IS NULL AND idAnalyse = :selectIdAnalyse ORDER BY dateHeureVisite";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("selectIdAnalyse", selectIdAnalyse);
		requete.setMaxResults(1);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat = requete.getResultList();
		DatabaseLink.close();
		if (listeResultat.isEmpty()) {
			labelDate.setText("Pas de visite disponible");
			labelHeure.setText("Pas de visite disponible");
		}
		else {
			for (Visite visite : listeResultat) {
				labelDate.setText(visite.getDateHeureVisite().toLocalDate().getDayOfMonth() + "/" + visite.getDateHeureVisite().toLocalDate().getMonthValue() + "/" + visite.getDateHeureVisite().toLocalDate().getYear());
				labelHeure.setText(visite.getDateHeureVisite().toLocalTime().toString());
			}
			buttonReserver.setDisable(false);
		}
	}
	
	@FXML
	protected void handleButtonRetourAccueil(ActionEvent event) throws IOException {
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
	protected void handleButtonReserver(ActionEvent event) throws IOException {
		montantAPayer = Float.parseFloat(comboBoxAnalyse.getValue().split(" - ")[2].split("€")[0]);
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/laboratoiredanalyses_hibernate/view/PaiementView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();

		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
}
