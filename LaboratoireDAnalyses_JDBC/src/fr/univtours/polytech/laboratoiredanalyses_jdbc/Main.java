package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.SQLException;

/**
 * @author Dorian GILBERT
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			// Connexion à la base de données
			DatabaseLink.connect("jdbc:mysql://127.0.0.1:3306/laboratoiredanalyses", "root", "root");
			// Création des tables
			DatabaseLink.creationTables();
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex);
			System.exit(1);
		}
	}
}
