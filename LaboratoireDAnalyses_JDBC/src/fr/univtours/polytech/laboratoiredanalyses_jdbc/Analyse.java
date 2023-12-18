package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorian GILBERT
 *
 */
public class Analyse {

	private int idAnalyse;

	private String nomAnalyse;
	
	private float prixAnalyse;

	private List<Medecin> listeMedecins;

	/**
	 * @param idAnalyse
	 * @param nomAnalyse
	 * @throws SQLException 
	 */
	public Analyse(String nomAnalyse, float prixAnalyse) throws SQLException {
		this.nomAnalyse = nomAnalyse;
		this.prixAnalyse = prixAnalyse;
		this.listeMedecins = new ArrayList<>();
		insertIntoAnalyse(nomAnalyse, prixAnalyse);
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(idAnalyse) FROM Analyse";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idAnalyse = rs.getInt("MAX(idAnalyse)");
		}
		//Libération des ressources liées au statement
		stmt.close();
	}

	/**
	 * @return the idAnalyse
	 */
	public int getIdAnalyse() {
		return idAnalyse;
	}

	/**
	 * @param idAnalyse the idAnalyse to set
	 */
	public void setIdAnalyse(int idAnalyse) {
		this.idAnalyse = idAnalyse;
	}

	/**
	 * @return the nomAnalyse
	 */
	public String getNomAnalyse() {
		return nomAnalyse;
	}

	/**
	 * @param nomAnalyse the nomAnalyse to set
	 */
	public void setNomAnalyse(String nomAnalyse) {
		this.nomAnalyse = nomAnalyse;
	}

	/**
	 * @return the prixAnalyse
	 */
	public float getPrixAnalyse() {
		return prixAnalyse;
	}

	/**
	 * @param prixAnalyse the prixAnalyse to set
	 */
	public void setPrixAnalyse(float prixAnalyse) {
		this.prixAnalyse = prixAnalyse;
	}

	/**
	 * @return the listeMedecins
	 */
	public List<Medecin> getListeMedecins() {
		return listeMedecins;
	}

	/**
	 * @param listeMedecins the listeMedecins to set
	 */
	public void setListeMedecins(List<Medecin> listeMedecins) {
		this.listeMedecins = listeMedecins;
	}
	
	public void ajouterAListeMedecins(Medecin medecin) {
		this.listeMedecins.add(medecin);
	}
	
	@Override
	public String toString() {
		return "Analyse [idAnalyse=" + idAnalyse + ", nomAnalyse=" + nomAnalyse + ", prixAnalyse=" + prixAnalyse + "]";
	}

	public static void createTableAnalyse() throws SQLException {
		System.out.println("Création de la table Analyse.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Analyse("
				+ "idAnalyse INT NOT NULL AUTO_INCREMENT,"
				+ "nomAnalyse VARCHAR(50),"
				+ "prixAnalyse DECIMAL(15,2),"
				+ "PRIMARY KEY(idAnalyse)"
				+ ")");
	}
	
	public static void insertIntoAnalyse(String nomAnalyse, float prixAnalyse) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Analyse(nomAnalyse, prixAnalyse) VALUES (?, ?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setString(1, nomAnalyse);
		prpdStmtInsert.setFloat(2, prixAnalyse);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
	public static void afficherListeAnalyses() throws SQLException {
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT idAnalyse, nomAnalyse, prixAnalyse FROM Analyse";
		//Exécution de la requête et stockage du résultat dans un objet ResultSet
		ResultSet rs = stmt.executeQuery(requete);
		System.out.println("Liste des analyses disponibles :");
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			System.out.println(rs.getInt("idAnalyse") + " - " + rs.getString("nomAnalyse") + " - " + rs.getFloat("prixAnalyse") + "€");
		}
		//Libération des ressources liées au statement
		stmt.close();
	}
}
