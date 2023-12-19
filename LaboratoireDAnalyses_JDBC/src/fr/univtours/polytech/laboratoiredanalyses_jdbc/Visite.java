package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
		reserverVisite(this.idVisite, patient.getNssPatient());
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
	
	public static boolean reserverVisite(int idVisite, long nssPatient) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT idVisite FROM Visite WHERE idVisite=? LIMIT 1");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelect.setInt(1, idVisite);
		// Execution de la requête du PreparedStatement et stockage du résultat dans un ResultSet
		ResultSet rs = prpdStmtSelect.executeQuery();
		if (!rs.next()) {
			System.out.println("La valeur saisie ne correspond pas à une visite");
			return false;
		} else {
			// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
			PreparedStatement prpdStmtUpdate = DatabaseLink.getConn().prepareStatement("UPDATE Visite SET nssPatient = ? WHERE idVisite = ?");
			// Mise en place du PreparedStatement avec les paramètres
			prpdStmtUpdate.setLong(1, nssPatient);
			prpdStmtUpdate.setInt(2, idVisite);
			// Execution de la requête du PreparedStatement
			prpdStmtUpdate.executeUpdate();
			// Libération des ressources liées au PreparedStatement
			prpdStmtUpdate.close();
		}
		// Libération des ressources liées au ResultSet
		rs.close();
		// Libération des ressources liées au PreparedStatement
		prpdStmtSelect.close();
		return true;
	}
	
	public static int afficherPremiereVisiteDisponible(int idAnalyse) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT idVisite, dateVisite, heureVisite FROM laboratoiredanalyses.Visite WHERE dateVisite >= NOW() AND nssPatient IS NULL AND idAnalyse = ? ORDER BY dateVisite LIMIT 1");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelect.setInt(1, idAnalyse);
		// Execution de la requête du PreparedStatement
		ResultSet rs = prpdStmtSelect.executeQuery();
		int idPremiereVisiteDispo = 0;
		int compteurLignesResultat = 0;
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			idPremiereVisiteDispo = rs.getInt("idVisite");
			System.out.println("La première visite est disponible le " + rs.getDate("dateVisite") + " à " + rs.getTime("heureVisite"));
			compteurLignesResultat++;
		}
		if (compteurLignesResultat == 0) {
			System.out.println("Aucune visite disponible.");
			return 0;
		}
		// Libération des ressources liées au PreparedStatement
		prpdStmtSelect.close();
		return idPremiereVisiteDispo;
	}
	
	public static void payerVisite(int idVisite, long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire) throws SQLException {
		Paiement.insertIntoPaiement(numCarteBancaire, cvvCarteBancaire, expCarteBancaire, idVisite);
	}
}
