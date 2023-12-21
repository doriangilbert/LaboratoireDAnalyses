package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

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
}
