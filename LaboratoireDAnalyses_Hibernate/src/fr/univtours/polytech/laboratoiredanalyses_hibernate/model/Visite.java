package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */

/**
 * Classe représentant une visite
 */
@Entity
@Table(name = "Visite")
public class Visite {
	
	/**
	 * Attribut privé représentant l'identifiant d'une visite
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVisite")
	private int idVisite;

	/**
	 * Attribut privé représentant la date et l'heure d'une visite
	 */
	@Column(name = "dateHeureVisite")
	private LocalDateTime dateHeureVisite;

	/**
	 * Attribut privé représentant le patient associé à une visite
	 */
	@ManyToOne
	@JoinColumn(name = "nssPatient")
	private Patient patient;

	/**
	 * Attribut privé représentant le paiement associé à une visite
	 */
	@OneToOne(mappedBy = "visite")
	private Paiement paiement;

	/**
	 * Attribut privé représentant l'analyse associée à une visite
	 */
	@ManyToOne
	@JoinColumn(name = "idAnalyse")
	private Analyse analyse;

	/**
	 * Constructeur par défaut d'une visite
	 */
	public Visite() {
	}
	
	/**
	 * Constructeur d'une visite
	 * @param dateHeureVisite La date et l'heure de la visite à créer
	 * @param analyse L'analyse associée à la visite à créer
	 */
	public Visite(LocalDateTime dateHeureVisite, Analyse analyse) {
		this.dateHeureVisite = dateHeureVisite;
		this.analyse = analyse;
	}

	/**
	 * Accesseur en lecture de l'identifiant d'une visite
	 * @return idVisite L'identifiant de la visite
	 */
	public int getIdVisite() {
		return idVisite;
	}

	/**
	 * Accesseur en écriture de l'identifiant d'une visite
	 * @param idVisite Nouvelle valeur de l'identifiant de la visite
	 */
	public void setIdVisite(int idVisite) {
		this.idVisite = idVisite;
	}

	/**
	 * Accesseur en lecture de la date et l'heure d'une visite
	 * @return dateHeureVisite La date et l'heure de la visite
	 */
	public LocalDateTime getDateHeureVisite() {
		return dateHeureVisite;
	}

	/**
	 * Accesseur en écriture de la date et l'heure d'une visite
	 * @param dateHeureVisite Nouvelle valeur de la date et l'heure de la visite
	 */
	public void setDateHeureVisite(LocalDateTime dateHeureVisite) {
		this.dateHeureVisite = dateHeureVisite;
	}

	/**
	 * Accesseur en lecture du patient associé à une visite
	 * @return patient Le patient associé à la visite
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Accesseur en écriture du patient associé à une visite
	 * @param patient Nouvelle valeur du patient associé à la visite
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Accesseur en lecture du paiement associé à une visite
	 * @return paiement Le paiement associé à la visite
	 */
	public Paiement getPaiement() {
		return paiement;
	}

	/**
	 * Accesseur en écriture du paiement associé à une visite
	 * @param paiement Nouvelle valeur du paiement associé à la visite
	 */
	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	/**
	 * Accesseur en lecture de l'analyse associée à une visite
	 * @return analyse L'analyse associée à la visite
	 */
	public Analyse getAnalyse() {
		return analyse;
	}

	/**
	 * Accesseur en écriture de l'analyse associée à une visite
	 * @param analyse Nouvelle valeur de l'analyse associée à la visite
	 */
	public void setAnalyse(Analyse analyse) {
		this.analyse = analyse;
	}

	/**
	 * Méthode permettant d'obtenir une chaine de caractères représentant une visite
	 */
	@Override
	public String toString() {
		return "Visite [idVisite=" + idVisite + ", dateHeureVisite=" + dateHeureVisite + ", patient=" + patient
				+ ", paiement=" + paiement + ", analyse=" + analyse + "]";
	}
	
	/**
	 * Méthode permettant de réserver une visite
	 * @param idVisite L'identifiant de la visite à réserver
	 * @param nssPatient Le numéro de sécurité sociale du patient pour lequel la visite est réservée
	 * @return vrai si la visite a été réservée, faux sinon
	 */
	public static void reserverVisite(int idVisite, long nssPatient) {
		String strRequete = "SELECT p FROM Patient p WHERE p.nssPatient = :nssPatient";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("nssPatient", nssPatient);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Patient> listeResultat = requete.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		Patient patient = null;
		// Si la liste de résultats n'est pas vide
		if (!listeResultat.isEmpty()) {
			// Parcours de la liste de résultats
			for (Patient p : listeResultat) {
				// Récupération du patient
				patient = p;
			}
		}
		
		String strRequete1 = "SELECT v FROM Visite v WHERE v.idVisite = :idVisite";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete1 = DatabaseLink.getSession().createQuery(strRequete1);
		// Attribution de la valeur à la variable de la requête
		requete1.setParameter("idVisite", idVisite);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat1 = requete1.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		// Si la liste de résultats n'est pas vide
		if (!listeResultat1.isEmpty()) {
			// Ouverture d'une session et d'une transaction hibernate
			DatabaseLink.open();
			// Parcours de la liste de résultats
			for (Visite visite : listeResultat1) {
				// Ajout du patient à la visite
				visite.setPatient(patient);
				// Mise à jour de la visite
				DatabaseLink.getSession().update(visite);
			}
			// Fermeture de la session et de la transaction hibernate
			DatabaseLink.close();
		}
	}
	
	/**
	 * Méthode permettant de payer une visite
	 * @param idVisite L'identifiant de la visite à payer
	 * @param numeroCarteBancaire Le numéro de carte bancaire utilisé pour payer la visite
	 * @param cvvCarteBancaire Le CVV de la carte bancaire utilisée pour payer la visite
	 * @param expCarteBancaire La date d'expiration de la carte bancaire utilisée pour payer la visite
	 * @return vrai si la visite a été payée, faux sinon
	 */
	public static void payerVisite(int idVisite, long numeroCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire) {
		String strRequete = "SELECT v FROM Visite v WHERE v.idVisite = :idVisite";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("idVisite", idVisite);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat = requete.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		// Si la liste de résultats n'est pas vide
		if (!listeResultat.isEmpty()) {
			// Ouverture d'une session et d'une transaction hibernate
			DatabaseLink.open();
			// Parcours de la liste de résultats
			for (Visite visite : listeResultat) {
				// Création d'un nouveau paiement
				Paiement paiement = new Paiement(numeroCarteBancaire, cvvCarteBancaire, expCarteBancaire, visite);
				// Sauvegarde du paiement
				DatabaseLink.getSession().save(paiement);
				// Ajout du paiement à la visite
				visite.setPaiement(paiement);
				// Mise à jour de la visite
				DatabaseLink.getSession().update(visite);
			}
			// Fermeture de la session et de la transaction hibernate
			DatabaseLink.close();
		}
    }
}
