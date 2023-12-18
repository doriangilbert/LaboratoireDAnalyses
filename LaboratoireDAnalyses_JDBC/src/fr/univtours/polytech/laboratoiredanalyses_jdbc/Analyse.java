package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorian GILBERT
 *
 */
public class Analyse {

	private int idAnalyse;

	private String nomAnalyse;

	private List<Medecin> listeMedecins;

	/**
	 * @param idAnalyse
	 * @param nomAnalyse
	 */
	public Analyse(int idAnalyse, String nomAnalyse) {
		this.idAnalyse = idAnalyse;
		this.nomAnalyse = nomAnalyse;
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
		return "Analyse [idAnalyse=" + idAnalyse + ", nomAnalyse=" + nomAnalyse + "]";
	}
	
	public static void createTableAnalyse() {
		// TODO
	}
	
	public static void insertAnalyse() {
		// TODO
	}
}
