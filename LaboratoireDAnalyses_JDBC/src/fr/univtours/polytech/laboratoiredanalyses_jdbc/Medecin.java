package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorian GILBERT
 *
 */
public class Medecin {

	private long nssMedecin;

	private String nomMedecin;

	private String prenomMedecin;

	private float salaireMedecin;

	private List<Analyse> listeAnalyses;

	/**
	 * @param nssMedecin
	 * @param nomMedecin
	 * @param prenomMedecin
	 * @param salaireMedecin
	 * @throws SQLException 
	 */
	public Medecin(long nssMedecin, String nomMedecin, String prenomMedecin, float salaireMedecin) throws SQLException {
		this.nssMedecin = nssMedecin;
		this.nomMedecin = nomMedecin;
		this.prenomMedecin = prenomMedecin;
		this.salaireMedecin = salaireMedecin;
		this.listeAnalyses = new ArrayList<>();
		insertIntoMedecin(nssMedecin, nomMedecin, prenomMedecin, salaireMedecin);
	}

	/**
	 * @return the nssMedecin
	 */
	public long getNssMedecin() {
		return nssMedecin;
	}

	/**
	 * @param nssMedecin the nssMedecin to set
	 */
	public void setNssMedecin(long nssMedecin) {
		this.nssMedecin = nssMedecin;
	}

	/**
	 * @return the nomMedecin
	 */
	public String getNomMedecin() {
		return nomMedecin;
	}

	/**
	 * @param nomMedecin the nomMedecin to set
	 */
	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	/**
	 * @return the prenomMedecin
	 */
	public String getPrenomMedecin() {
		return prenomMedecin;
	}

	/**
	 * @param prenomMedecin the prenomMedecin to set
	 */
	public void setPrenomMedecin(String prenomMedecin) {
		this.prenomMedecin = prenomMedecin;
	}

	/**
	 * @return the salaireMedecin
	 */
	public float getSalaireMedecin() {
		return salaireMedecin;
	}

	/**
	 * @param salaireMedecin the salaireMedecin to set
	 */
	public void setSalaireMedecin(float salaireMedecin) {
		this.salaireMedecin = salaireMedecin;
	}

	/**
	 * @return the listeAnalyses
	 */
	public List<Analyse> getListeAnalyses() {
		return listeAnalyses;
	}

	/**
	 * @param listeAnalyses the listeAnalyses to set
	 */
	public void setListeAnalyses(List<Analyse> listeAnalyses) {
		this.listeAnalyses = listeAnalyses;
	}
	
	public void ajouterAListeAnalyses(Analyse analyse) {
		this.listeAnalyses.add(analyse);
	}

	@Override
	public String toString() {
		return "Medecin [nssMedecin=" + nssMedecin + ", nomMedecin=" + nomMedecin + ", prenomMedecin=" + prenomMedecin
				+ ", salaireMedecin=" + salaireMedecin + "]";
	}
	
	public static void createTableMedecin() throws SQLException {
		System.out.println("Création de la table Medecin.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Medecin("
				+ "nssMedecin BIGINT NOT NULL,"
				+ "nomMedecin VARCHAR(50),"
				+ "prenomMedecin VARCHAR(50),"
				+ "salaireMedecin DECIMAL(15,2),"
				+ "PRIMARY KEY(nssMedecin)"
				+ ")");
	}
	
	public static void insertIntoMedecin(long nssMedecin, String nomMedecin, String prenomMedecin, float salaireMedecin) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Medecin(nssMedecin, nomMedecin, prenomMedecin, salaireMedecin) VALUES (?, ?, ?, ?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setLong(1, nssMedecin);
		prpdStmtInsert.setString(2, nomMedecin);
		prpdStmtInsert.setString(3, prenomMedecin);
		prpdStmtInsert.setFloat(4, salaireMedecin);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
	public static void createTableEstAutorise() throws SQLException {
		System.out.println("Création de la table Est_autorise.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Est_autorise("
				+ "idAnalyse INT NOT NULL,"
				+ "nssMedecin BIGINT NOT NULL,"
				+ "PRIMARY KEY(idAnalyse, nssMedecin),"
				+ "FOREIGN KEY(idAnalyse) REFERENCES Analyse(idAnalyse),"
				+ "FOREIGN KEY(nssMedecin) REFERENCES Medecin(nssMedecin)"
				+ ")");
	}
	
	public static void insertIntoEstAutorise(int idAnalyse, long nssMedecin) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Est_autorise(idAnalyse, nssMedecin) VALUES (?, ?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setInt(1, idAnalyse);
		prpdStmtInsert.setLong(2, nssMedecin);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
	public void autoriserAnalyse(Analyse analyse) throws SQLException {
		insertIntoEstAutorise(analyse.getIdAnalyse(), this.getNssMedecin());
		ajouterAListeAnalyses(analyse);
		analyse.ajouterAListeMedecins(this);

	}
}
