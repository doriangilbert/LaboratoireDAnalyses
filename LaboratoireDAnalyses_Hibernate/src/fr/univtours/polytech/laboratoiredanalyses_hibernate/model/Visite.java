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
	
	public static void reserverVisite(int idVisite, long nssPatient) {
		String strRequete = "SELECT p FROM Patient p WHERE p.nssPatient = :nssPatient";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("nssPatient", nssPatient);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Patient> listeResultat = requete.getResultList();
		DatabaseLink.close();
		Patient patient = null;
		if (!listeResultat.isEmpty()) {
			for (Patient p : listeResultat) {
				patient = p;
			}
		}
		
		String strRequete1 = "SELECT v FROM Visite v WHERE v.idVisite = :idVisite";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete1 = DatabaseLink.getSession().createQuery(strRequete1);
		// Attribution de la valeur à la variable de la requête
		requete1.setParameter("idVisite", idVisite);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat1 = requete1.getResultList();
		DatabaseLink.close();
		if (!listeResultat1.isEmpty()) {
			DatabaseLink.open();
			for (Visite visite : listeResultat1) {
				visite.setPatient(patient);
				DatabaseLink.getSession().update(visite);
			}
			DatabaseLink.close();
		}
	}
	
	public static void payerVisite(int idVisite, long numeroCarteBancaire, int cvvCarteBancaire, LocalDate expCarteBancaire) {
		String strRequete = "SELECT v FROM Visite v WHERE v.idVisite = :idVisite";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("idVisite", idVisite);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Visite> listeResultat = requete.getResultList();
		DatabaseLink.close();
		if (!listeResultat.isEmpty()) {
			DatabaseLink.open();
			for (Visite visite : listeResultat) {
				Paiement paiement = new Paiement(numeroCarteBancaire, cvvCarteBancaire, expCarteBancaire, visite);
				DatabaseLink.getSession().save(paiement);
				visite.setPaiement(paiement);
				DatabaseLink.getSession().update(visite);
			}
			DatabaseLink.close();
		}
    }
}
