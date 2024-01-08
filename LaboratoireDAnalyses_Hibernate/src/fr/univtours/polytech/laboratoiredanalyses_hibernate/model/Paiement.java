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

/**
 * Classe représentant un paiement
 */
@Entity
@Table(name = "Paiement")
public class Paiement {
	
	/**
	 * Attribut privé représentant l'identifiant d'un paiement
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPaiement")
	private int idPaiement;

	/**
	 * Attribut privé représentant le numéro de carte bancaire d'un paiement
	 */
	@Column(name = "numCarteBancaire")
	private long numCarteBancaire;

	/**
	 * Attribut privé représentant le CVV d'une carte bancaire d'un paiement
	 */
	@Column(name = "cvvCarteBancaire")
	private int cvvCarteBancaire;

	/**
	 * Attribut privé représentant la date d'expiration d'une carte bancaire d'un paiement
	 */
	@Column(name = "expCarteBancaire")
	private LocalDate expCarteBancaire;

	/**
	 * Attribut privé représentant la visite associée à un paiement
	 */
	@OneToOne
	@JoinColumn(name = "idVisite")
	private Visite visite;

	/**
	 * Constructeur par défaut d'un paiement
	 */
	public Paiement() {
	}
	
	/**
	 * Constructeur d'un paiement
	 * @param numCarteBancaire Le numéro de carte bancaire du paiement à créer
	 * @param cvvCarteBancaire Le CVV de la carte bancaire du paiement à créer
	 * @param expCarteBancaire La date d'expiration de la carte bancaire du paiement à créer
	 * @param visite La visite associée au paiement à créer
	 */
	public Paiement(long numCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire, Visite visite) {
		this.numCarteBancaire = numCarteBancaire;
		this.cvvCarteBancaire = cvvCarteBancaire;
		this.expCarteBancaire = expCarteBancaire;
		this.visite = visite;
		visite.setPaiement(this);
	}

	/**
	 * Accesseur en lecture de l'identifiant d'un paiement
	 * @return idPaiement L'identifiant du paiement
	 */
	public int getIdPaiement() {
		return idPaiement;
	}

	/**
	 * Accesseur en écriture de l'identifiant d'un paiement
	 * @param idPaiement Nouvelle valeur de l'identifiant du paiement
	 */
	public void setIdPaiement(int idPaiement) {
		this.idPaiement = idPaiement;
	}

	/**
	 * Accesseur en lecture du numéro de carte bancaire d'un paiement
	 * @return numCarteBancaire Le numéro de carte bancaire du paiement
	 */
	public long getNumCarteBancaire() {
		return numCarteBancaire;
	}

	/**
	 * Accesseur en écriture du numéro de carte bancaire d'un paiement
	 * @param numCarteBancaire Nouvelle valeur du numéro de carte bancaire du paiement
	 */
	public void setNumCarteBancaire(long numCarteBancaire) {
		this.numCarteBancaire = numCarteBancaire;
	}

	/**
	 * Accesseur en lecture du CVV d'une carte bancaire d'un paiement
	 * @return cvvCarteBancaire Le CVV de la carte bancaire du paiement
	 */
	public int getCvvCarteBancaire() {
		return cvvCarteBancaire;
	}

	/**
	 * Accesseur en écriture du CVV d'une carte bancaire d'un paiement
	 * @param cvvCarteBancaire Nouvelle valeur du CVV de la carte bancaire du paiement
	 */
	public void setCvvCarteBancaire(int cvvCarteBancaire) {
		this.cvvCarteBancaire = cvvCarteBancaire;
	}

	/**
	 * Accesseur en lecture de la date d'expiration d'une carte bancaire d'un paiement
	 * @return expCarteBancaire La date d'expiration de la carte bancaire du paiement
	 */
	public LocalDate getExpCarteBancaire() {
		return expCarteBancaire;
	}

	/**
	 * Accesseur en écriture de la date d'expiration d'une carte bancaire d'un paiement
	 * @param expCarteBancaire Nouvelle valeur de la date d'expiration de la carte bancaire du paiement
	 */
	public void setExpCarteBancaire(LocalDate expCarteBancaire) {
		this.expCarteBancaire = expCarteBancaire;
	}

	/**
	 * Accesseur en lecture de la visite associée à un paiement
	 * @return visite La visite associée au paiement
	 */
	public Visite getVisite() {
		return visite;
	}

	/**
	 * Accesseur en écriture de la visite associée à un paiement
	 * @param visite Nouvelle valeur de la visite associée au paiement
	 */
	public void setVisite(Visite visite) {
		this.visite = visite;
	}

	/**
	 * Méthode permettant d'obtenir une chaine de caractères représentant un paiement
	 */
	@Override
	public String toString() {
		return "Paiement [idPaiement=" + idPaiement + ", numCarteBancaire=" + numCarteBancaire + ", cvvCarteBancaire="
				+ cvvCarteBancaire + ", expCarteBancaire=" + expCarteBancaire + ", visite=" + visite + "]";
	}
}
