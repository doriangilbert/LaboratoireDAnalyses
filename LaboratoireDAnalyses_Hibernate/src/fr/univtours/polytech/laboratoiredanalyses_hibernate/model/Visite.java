package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */
@Entity
@Table(name = "Visite")
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVisite")
	private int idVisite;

	@Column(name = "dateHeureVisite")
	private LocalDateTime dateHeureVisite;

	@ManyToOne
	@JoinColumn(name = "nssPatient")
	private Patient patient;

	@OneToOne(mappedBy = "visite")
	private Paiement paiement;

	@ManyToOne
	@JoinColumn(name = "idAnalyse")
	private Analyse analyse;

	public Visite() {
	}
	
	/**
	 * @param dateHeureVisite
	 * @param analyse
	 */
	public Visite(LocalDateTime dateHeureVisite, Analyse analyse) {
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
