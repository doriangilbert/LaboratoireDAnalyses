package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */

@Entity
@Table(name = "Paiement")
public class Paiement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPaiement")
	private int idPaiement;

	@Column(name = "numCarteBancaire")
	private long numCarteBancaire;

	@Column(name = "cvvCarteBancaire")
	private int cvvCarteBancaire;

	@Column(name = "expCarteBancaire")
	private LocalDate expCarteBancaire;

	@OneToOne
	@JoinColumn(name = "idVisite")
	private Visite visite;

	/**
	 * @param numCarteBancaire
	 * @param cvvCarteBancaire
	 * @param expCarteBancaire
	 * @param visite
	 */
	public Paiement(long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire, Visite visite) {
		this.numCarteBancaire = numCarteBancaire;
		this.cvvCarteBancaire = cvvCarteBancaire;
		this.expCarteBancaire = expCarteBancaire;
		this.visite = visite;
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
}
