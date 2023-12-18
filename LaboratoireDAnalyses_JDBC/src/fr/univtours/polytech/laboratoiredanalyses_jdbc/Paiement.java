package fr.univtours.polytech.laboratoiredanalyses_jdbc;

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
	 */
	public Paiement(int idPaiement, long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire,
			Visite visite) {
		this.idPaiement = idPaiement;
		this.numCarteBancaire = numCarteBancaire;
		this.cvvCarteBancaire = cvvCarteBancaire;
		this.expCarteBancaire = expCarteBancaire;
		this.visite = visite;
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
	
	public static void createTablePaiement() {
		// TODO
	}
	
	public static void insertPaiement() {
		// TODO
	}
}
