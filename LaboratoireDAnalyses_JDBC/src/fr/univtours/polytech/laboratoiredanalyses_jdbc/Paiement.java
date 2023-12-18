package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * @author Dorian GILBERT
 *
 */
public class Paiement {

	private int idPaiement;

	private long numCarteBancaire;

	private int cvvCarteBancaire;

	private LocalDate expCarteBancaire;

	private Visite visite;

	/**
	 * @param idPaiement
	 * @param numCarteBancaire
	 * @param cvvCarteBancaire
	 * @param expCarteBancaire
	 * @param visite
	 * @throws SQLException 
	 */
	public Paiement(long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire, Visite visite) throws SQLException {
		this.numCarteBancaire = numCarteBancaire;
		this.cvvCarteBancaire = cvvCarteBancaire;
		this.expCarteBancaire = expCarteBancaire;
		this.visite = visite;
		insertIntoPaiement(numCarteBancaire, cvvCarteBancaire, expCarteBancaire, visite.getIdVisite());
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(idPaiement) FROM Paiement";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idPaiement = rs.getInt("MAX(idPaiement)");
		}
		//Libération des ressources liées au statement
		stmt.close();
		visite.setPaiement(this);
	}

	/**
	 * @return the idPaiement
	 */
	public int getIdPaiement() {
		return idPaiement;
	}

	/**
	 * @param idPaiement the idPaiement to set
	 */
	public void setIdPaiement(int idPaiement) {
		this.idPaiement = idPaiement;
	}

	/**
	 * @return the numCarteBancaire
	 */
	public long getNumCarteBancaire() {
		return numCarteBancaire;
	}

	/**
	 * @param numCarteBancaire the numCarteBancaire to set
	 */
	public void setNumCarteBancaire(long numCarteBancaire) {
		this.numCarteBancaire = numCarteBancaire;
	}

	/**
	 * @return the cvvCarteBancaire
	 */
	public int getCvvCarteBancaire() {
		return cvvCarteBancaire;
	}

	/**
	 * @param cvvCarteBancaire the cvvCarteBancaire to set
	 */
	public void setCvvCarteBancaire(int cvvCarteBancaire) {
		this.cvvCarteBancaire = cvvCarteBancaire;
	}

	/**
	 * @return the expCarteBancaire
	 */
	public LocalDate getExpCarteBancaire() {
		return expCarteBancaire;
	}

	/**
	 * @param expCarteBancaire the expCarteBancaire to set
	 */
	public void setExpCarteBancaire(LocalDate expCarteBancaire) {
		this.expCarteBancaire = expCarteBancaire;
	}

	/**
	 * @return the visite
	 */
	public Visite getVisite() {
		return visite;
	}

	/**
	 * @param visite the visite to set
	 */
	public void setVisite(Visite visite) {
		this.visite = visite;
	}

	@Override
	public String toString() {
		return "Paiement [idPaiement=" + idPaiement + ", numCarteBancaire=" + numCarteBancaire + ", cvvCarteBancaire="
				+ cvvCarteBancaire + ", expCarteBancaire=" + expCarteBancaire + ", visite=" + visite + "]";
	}
	
	public static void createTablePaiement() throws SQLException {
		System.out.println("Création de la table Paiement.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Paiement("
				+ "idPaiement INT NOT NULL AUTO_INCREMENT,"
				+ "numCarteBancaire BIGINT,"
				+ "cvvCarteBancaire INT,"
				+ "expCarteBancaire DATE,"
				+ "idVisite INT NOT NULL,"
				+ "PRIMARY KEY(idPaiement),"
				+ "UNIQUE(idVisite),"
				+ "FOREIGN KEY(idVisite) REFERENCES Visite(idVisite)"
				+ ")");
	}
	
	public static void insertIntoPaiement(long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire, int idVisite) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Paiement(numCarteBancaire, cvvCarteBancaire, expCarteBancaire, idVisite) VALUES (?, ?, ?, ?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setLong(1, numCarteBancaire);
		prpdStmtInsert.setInt(2, cvvCarteBancaire);
		prpdStmtInsert.setDate(3, java.sql.Date.valueOf(expCarteBancaire));
		prpdStmtInsert.setInt(4, idVisite);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
}
