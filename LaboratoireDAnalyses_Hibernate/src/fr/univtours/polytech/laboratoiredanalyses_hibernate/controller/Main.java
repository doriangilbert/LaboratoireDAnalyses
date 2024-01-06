package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;
/**
 * 
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Analyse;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.DatabaseLink;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Medecin;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Paiement;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Patient;
import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Visite;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;
	public static Parent root;
	
	public static void peuplementTables() {
		Patient patient1 = new Patient(123456789123456L, "Alain", "TERIEUR", "password");
		DatabaseLink.getSession().save(patient1);
		Patient patient2 = new Patient(987654321987654L, "Alex", "TERIEUR", "password");
		DatabaseLink.getSession().save(patient2);
		
		Analyse analyse1 = new Analyse("Hemogramme", 50);
		DatabaseLink.getSession().save(analyse1);
		Analyse analyse2 = new Analyse("Groupe sanguin", 25);
		DatabaseLink.getSession().save(analyse2);
		Analyse analyse3 = new Analyse("Vitesse de sédimentation", 75);
		DatabaseLink.getSession().save(analyse3);
		
		Medecin medecin1 = new Medecin(147852369147852L, "Jean", "DUPONT", 3000);
		DatabaseLink.getSession().save(medecin1);
		Medecin medecin2 = new Medecin(369852147369852L, "Pierre", "JACQUES", 5500);
		DatabaseLink.getSession().save(medecin2);
		
		Visite visite1 = new Visite(LocalDateTime.of(2023, 12, 5, 9, 30), analyse2);
		DatabaseLink.getSession().save(visite1);
		Visite visite2 = new Visite(LocalDateTime.of(2024, 1, 3, 14, 0), analyse3);
		DatabaseLink.getSession().save(visite2);
		Visite visite3 = new Visite(LocalDateTime.of(2024, 1, 11, 17, 15), analyse1);
		DatabaseLink.getSession().save(visite3);
		Visite visite4 = new Visite(LocalDateTime.of(2024, 1, 25, 13, 30), analyse1);
		DatabaseLink.getSession().save(visite4);
		Visite visite5 = new Visite(LocalDateTime.of(2024, 1, 25, 15, 20), analyse3);
		DatabaseLink.getSession().save(visite5);
		Visite visite6 = new Visite(LocalDateTime.of(2024, 2, 6, 8, 15), analyse2);
		DatabaseLink.getSession().save(visite6);
		Visite visite7 = new Visite(LocalDateTime.of(2024, 2, 6, 9, 45), analyse2);
		DatabaseLink.getSession().save(visite7);
		
		visite1.setPatient(patient2);
		DatabaseLink.getSession().save(visite1);
		Paiement paiement1 = new Paiement(1234432123144123L, 852, LocalDate.of(2027, 8, 1), visite1);
		DatabaseLink.getSession().save(paiement1);
		
		medecin1.autoriserAnalyse(analyse2);
		medecin2.autoriserAnalyse(analyse1);
		medecin2.autoriserAnalyse(analyse3);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		Main.primaryStage = primaryStage;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/laboratoiredanalyses_hibernate/view/PageAccueilView.fxml"));

			Scene scene = new Scene(root, 640, 400);
			Main.primaryStage.setTitle("LaboratoireDAnalyses");
			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Connexion à la base de données
		DatabaseLink.connect();
		// Ouverture de la transaction
		org.hibernate.Transaction tr = DatabaseLink.getSession().beginTransaction();
		
		peuplementTables();
		
		// Validation de la transaction (validation des opérations effectuées)
		tr.commit();
		// Fermeture de la session Hibernate
		DatabaseLink.getSessFact().close();

		
		launch(args);
		
	}

}
