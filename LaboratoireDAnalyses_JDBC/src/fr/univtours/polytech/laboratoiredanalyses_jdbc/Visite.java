package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * @author Dorian GILBERT
 *
 */
public class Visite {

	private int idVisite;

	private LocalDateTime dateHeureVisite;

	private Patient patient;

	private Paiement paiement;

	private Analyse analyse;

	/**
	 * @param idVisite
	 * @param dateHeureVisite
	 * @param patient
	 * @param paiement
	 * @param analyse
	 * @throws SQLException 
	 */
	public Visite(LocalDateTime dateHeureVisite, Analyse analyse) throws SQLException {
		this.dateHeureVisite = dateHeureVisite;
		this.analyse = analyse;
		insertIntoVisite(dateHeureVisite, analyse.getIdAnalyse());
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(idVisite) FROM Visite";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idVisite = rs.getInt("MAX(idVisite)");
		}
		//Libération des ressources liées au statement
		stmt.close();
	}

	/**
	 * @return the idVisite
	 */
	public int getIdVisite() {
		return idVisite;
	}

	/**
	 * @param idVisite the idVisite to set
	 */
	public void setIdVisite(int idVisite) {
		this.idVisite = idVisite;
	}

	/**
	 * @return the dateHeureVisite
	 */
	public LocalDateTime getDateHeureVisite() {
		return dateHeureVisite;
	}

	/**
	 * @param dateHeureVisite the dateHeureVisite to set
	 */
	public void setDateHeureVisite(LocalDateTime dateHeureVisite) {
		this.dateHeureVisite = dateHeureVisite;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 * @throws SQLException 
	 */
	public void setPatient(Patient patient) throws SQLException {
		this.patient = patient;
		this.associerPatient(patient);
	}

	/**
	 * @return the paiement
	 */
	public Paiement getPaiement() {
		return paiement;
	}

	/**
	 * @param paiement the paiement to set
	 */
	public void setPaiement(Paiement paiement) throws SQLException {
		this.paiement = paiement;
	}

	/**
	 * @return the analyse
	 */
	public Analyse getAnalyse() {
		return analyse;
	}

	/**
	 * @param analyse the analyse to set
	 */
	public void setAnalyse(Analyse analyse) {
		this.analyse = analyse;
	}

	@Override
	public String toString() {
		return "Visite [idVisite=" + idVisite + ", dateHeureVisite=" + dateHeureVisite + ", patient=" + patient
				+ ", paiement=" + paiement + ", analyse=" + analyse + "]";
	}
	
	public static void createTableVisite() throws SQLException {
		System.out.println("Création de la table Visite.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Visite("
				+ "idVisite INT NOT NULL AUTO_INCREMENT,"
				+ "dateVisite DATE,"
				+ "heureVisite TIME,"
				+ "idAnalyse INT,"
				+ "nssPatient BIGINT,"
				+ "PRIMARY KEY(idVisite),"
				+ "FOREIGN KEY(idAnalyse) REFERENCES Analyse(idAnalyse),"
				+ "FOREIGN KEY(nssPatient) REFERENCES Patient(nssPatient)"
				+ ")");
	}
	
	public static void insertIntoVisite(LocalDateTime dateHeureVisite, int idAnalyse) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Visite(dateVisite, heureVisite, idAnalyse) VALUES (?, ?, ?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setObject(1, dateHeureVisite.toLocalDate());
		prpdStmtInsert.setObject(2, dateHeureVisite.toLocalTime());
		prpdStmtInsert.setInt(3, idAnalyse);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
	public void associerPatient(Patient patient) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtUpdate = DatabaseLink.getConn().prepareStatement("UPDATE Visite SET nssPatient = ? WHERE idVisite = ?");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtUpdate.setLong(1, patient.getNssPatient());
		prpdStmtUpdate.setInt(2, this.idVisite);
		// Execution de la requête du PreparedStatement
		prpdStmtUpdate.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtUpdate.close();
		
		patient.ajouterAListeVisites(this);
	}
	
	public static void afficherListeVisitesDisponibles() throws SQLException {
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT dateVisite, heureVisite, nomAnalyse FROM Visite NATURAL JOIN Analyse WHERE dateVisite >= NOW() AND nssPatient IS NULL";
		//Exécution de la requête et stockage du résultat dans un objet ResultSet
		ResultSet rs = stmt.executeQuery(requete);
		System.out.println("Liste des visites disponibles :");
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			System.out.println(rs.getDate("dateVisite") + " " + rs.getTime("heureVisite") + " " + rs.getString("nomAnalyse"));
		}
		//Libération des ressources liées au statement
		stmt.close();
	}
}
