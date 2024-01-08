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

/**
 * Classe représentant le controleur de la page de réservation
 */
public class ReservationController {
	
	/**
	 * Attribut privé représentant la combo box des analyses
	 */
	@FXML
	private ComboBox<String> comboBoxAnalyse;
	
	/**
	 * Attribut privé représentant le label de la date de la visite
	 */
	@FXML
	private Label labelDate;
	
	/**
	 * Attribut privé représentant le label de l'heure de la visite
	 */
	@FXML
	private Label labelHeure;
	
	/**
	 * Attribut privé représentant le bouton "Réserver"
	 */
	@FXML
	private Button buttonReserver;
	
	/**
	 * Attribut privé représentant le montant à payer
	 */
	private static float montantAPayer;
	
	/**
	 * Attribut privé représentant l'identifiant de la visite
	 */
	private static int idVisite;

	/**
	 * Accesseur en lecture du montant à payer
	 * @return montantAPayer Le montant à payer
	 */
	public static float getMontantAPayer() {
		return montantAPayer;
	}

	/**
	 * Accesseur en écriture du montant à payer
	 * @param montantAPayer Nouvelle valeur du montant à payer
	 */
	public static void setMontantAPayer(float montantAPayer) {
		ReservationController.montantAPayer = montantAPayer;
	}

	/**
	 * Accesseur en lecture de l'identifiant de la visite
	 * @return idVisite L'identifiant de la visite
	 */
	public static int getIdVisite() {
		return idVisite;
	}

	/**
	 * Accesseur en écriture de l'identifiant de la visite
	 * @param idVisite Nouvelle valeur de l'identifiant de la visite
	 */
	public static void setIdVisite(int idVisite) {
		ReservationController.idVisite = idVisite;
	}

	/**
	 * Méthode d'initialisation de la page de réservation
	 */
	@FXML
	private void initialize() {
		String strRequete = "SELECT a FROM Analyse a";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Analyse> listeResultat = requete.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		// Création d'une liste d'analyses à mettre dans la combo box
		ObservableList<String> listeAnalyses = FXCollections.observableArrayList();
		// Ajout des analyses dans la liste
		for (Analyse analyse : listeResultat) {
			listeAnalyses.add(analyse.getIdAnalyse() + " - " + analyse.getNomAnalyse() + " - " + analyse.getPrixAnalyse() + "€");
		}
		// Ajout de la liste des analyses dans la combo box
		comboBoxAnalyse.setItems(listeAnalyses);
	}
	
	/**
	 * Méthode permettant de gérer le choix d'une élément dans la combo box des analyses
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleComboBoxAnalyse(ActionEvent event) throws IOException {
		// Récupération de l'identifiant de l'analyse sélectionnée
		int selectIdAnalyse = Integer.parseInt(comboBoxAnalyse.getValue().split(" - ")[0]);
		String strRequete = "SELECT v FROM Visite v WHERE dateHeureVisite >= NOW() AND nssPatient IS NULL AND idAnalyse = :selectIdAnalyse ORDER BY dateHeureVisite";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("selectIdAnalyse", selectIdAnalyse);
		requete.setMaxResults(1);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat = requete.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		// Si la liste de résultats est vide, on indique qu'il n'y a pas de visite disponible, sinon on affiche la date et l'heure de la première visite disponible
		if (listeResultat.isEmpty()) {
			labelDate.setText("Pas de visite disponible");
			labelHeure.setText("Pas de visite disponible");
		}
		else {
			for (Visite visite : listeResultat) {
				// Affichage de la date et de l'heure de la visite
				labelDate.setText(visite.getDateHeureVisite().toLocalDate().getDayOfMonth() + "/" + visite.getDateHeureVisite().toLocalDate().getMonthValue() + "/" + visite.getDateHeureVisite().toLocalDate().getYear());
				labelHeure.setText(visite.getDateHeureVisite().toLocalTime().toString());
				// Récupération de l'identifiant de la visite
				idVisite = visite.getIdVisite();
			}
			// Activation du bouton de réservation
			buttonReserver.setDisable(false);
		}
	}
	
	/**
	 * Méthode permettant de gérer un clic sur le bouton de retour à l'accueil
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleButtonRetourAccueil(ActionEvent event) throws IOException {
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
	 * Méthode permettant de gérer un clic sur le bouton de réservation
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleButtonReserver(ActionEvent event) throws IOException {
		// Récupération du montant à payer
		montantAPayer = Float.parseFloat(comboBoxAnalyse.getValue().split(" - ")[2].split("€")[0]);
		// Affichage de la page de paiement
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
