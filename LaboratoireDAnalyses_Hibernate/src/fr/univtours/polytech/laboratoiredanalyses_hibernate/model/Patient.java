package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */

@Entity
@Table(name = "Patient")
public class Patient {
	@Id
	@Column(name = "nssPatient")
	private long nssPatient;

	@Column(name = "nomPatient")
	private String nomPatient;

	@Column(name = "prenomPatient")
	private String prenomPatient;

	@Column(name = "mdpPatient")
	private String mdpPatient;

	@OneToMany(mappedBy = "patient")
	private List<Visite> listeVisites;

	public Patient() {
		this.listeVisites = new ArrayList<>();
	}
	
	/**
	 * @param nssPatient
	 * @param nomPatient
	 * @param prenomPatient
	 * @param mdpPatient
	 */
	public Patient(long nssPatient, String nomPatient, String prenomPatient, String mdpPatient) {
		this.nssPatient = nssPatient;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.mdpPatient = mdpPatient;
		this.listeVisites = new ArrayList<>();
	}

	/**
	 * @return the nssPatient
	 */
	public long getNssPatient() {
		return nssPatient;
	}

	/**
	 * @param nssPatient the nssPatient to set
	 */
	public void setNssPatient(long nssPatient) {
		this.nssPatient = nssPatient;
	}

	/**
	 * @return the nomPatient
	 */
	public String getNomPatient() {
		return nomPatient;
	}

	/**
	 * @param nomPatient the nomPatient to set
	 */
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	/**
	 * @return the prenomPatient
	 */
	public String getPrenomPatient() {
		return prenomPatient;
	}

	/**
	 * @param prenomPatient the prenomPatient to set
	 */
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	/**
	 * @return the mdpPatient
	 */
	public String getMdpPatient() {
		return mdpPatient;
	}

	/**
	 * @param mdpPatient the mdpPatient to set
	 */
	public void setMdpPatient(String mdpPatient) {
		this.mdpPatient = mdpPatient;
	}

	/**
	 * @return the listeVisites
	 */
	public List<Visite> getListeVisites() {
		return listeVisites;
	}

	/**
	 * @param listeVisites the listeVisites to set
	 */
	public void setListeVisites(List<Visite> listeVisites) {
		this.listeVisites = listeVisites;
	}

	public void ajouterAListeVisites(Visite visite) {
		this.listeVisites.add(visite);
	}

	@Override
	public String toString() {
		return "Patient [nssPatient=" + nssPatient + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient
				+ ", mdpPatient=" + mdpPatient + "]";
	}

	public static boolean verificationIdentifiants(long nss, String mdp) {
		// Requête permettant de récupérer les identifiants du patient dans la base de données si ils existent
		String strRequete = "SELECT nssPatient FROM Patient WHERE nssPatient = :nss AND mdpPatient = :mdp";
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("nss", nss);
		requete.setParameter("mdp", mdp);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Patient> listeResultat = requete.getResultList();
		DatabaseLink.close();
		boolean valide = false;
		if (listeResultat.isEmpty()) {
			valide = false;
		} else {
			valide = true;
		}
		return valide;
	}
}
