package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.SQLException;
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
	 */
	public Analyse(int idAnalyse, String nomAnalyse, float prixAnalyse) {
		this.idAnalyse = idAnalyse;
		this.nomAnalyse = nomAnalyse;
		this.prixAnalyse = prixAnalyse;
		this.listeMedecins = new ArrayList<>();
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
	
	public static void insertIntoAnalyse() {
		// TODO
	}
}
