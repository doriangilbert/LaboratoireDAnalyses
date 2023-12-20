package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Dorian GILBERT
 *
 */
public class DatabaseLink {
	private static Connection conn = null;

	/**
	 * @return the conn
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public static void setConn(Connection conn) {
		DatabaseLink.conn = conn;
	}

	/**
	 * Méthode publique permettant de se connecter à la base de données
	 * @param databaseUrl
	 * @param username
	 * @param password
	 */
	public static void connect(String databaseUrl, String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouvé!");
			System.exit(1);
		}
		try {
			System.out.println("Connexion à la base de données ...");
			conn = DriverManager.getConnection(databaseUrl, username, password);
			System.out.println("Base de données connectée.");
		} catch (SQLException ex2) {
			System.out.println("Erreur JDBC: " + ex2);
			System.exit(1);
		}
	}
	
	public static void createTable(String requete) throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	public static void creationTables() throws SQLException {
		System.out.println("Création des tables ...");
		
	}
}
