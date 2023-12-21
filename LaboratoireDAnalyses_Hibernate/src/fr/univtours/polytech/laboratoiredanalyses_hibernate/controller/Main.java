package fr.univtours.polytech.laboratoiredanalyses_hibernate.controller;
/**
 * 
 */

import fr.univtours.polytech.laboratoiredanalyses_hibernate.model.DatabaseLink;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;
	public static Parent root;
	
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
		
		org.hibernate.Transaction tr = DatabaseLink.getSession().beginTransaction();
		
		// Validation de la transaction (validation des opérations effectuées)
		tr.commit();
		// Fermeture de la session Hibernate
		DatabaseLink.getSessFact().close();

		
		launch(args);
		
	}

}
