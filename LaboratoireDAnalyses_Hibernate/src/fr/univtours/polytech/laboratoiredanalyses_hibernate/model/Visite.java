package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.sql.SQLException;
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
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
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
	public void setPaiement(Paiement paiement) {
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
}
