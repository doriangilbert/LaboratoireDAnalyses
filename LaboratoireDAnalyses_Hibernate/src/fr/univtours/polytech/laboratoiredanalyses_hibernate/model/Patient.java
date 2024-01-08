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

/**
 * Classe représentant un patient
 */
@Entity
@Table(name = "Patient")
public class Patient {
	
	/**
	 * Attribut privé représentant le numéro de sécurité sociale d'un patient
	 */
	@Id
	@Column(name = "nssPatient")
	private long nssPatient;

	/**
	 * Attribut privé représentant le nom d'un patient
	 */
	@Column(name = "nomPatient")
	private String nomPatient;

	/**
	 * Attribut privé représentant le prénom d'un patient
	 */
	@Column(name = "prenomPatient")
	private String prenomPatient;

	/**
	 * Attribut privé représentant le mot de passe d'un patient
	 */
	@Column(name = "mdpPatient")
	private String mdpPatient;

	/**
	 * Attribut privé représentant la liste des visites d'un patient
	 */
	@OneToMany(mappedBy = "patient")
	private List<Visite> listeVisites;

	/**
	 * Constructeur par défaut d'un patient
	 */
	public Patient() {
		this.listeVisites = new ArrayList<>();
	}
	
	/**
	 * Constructeur d'un patient
	 * @param nssPatient Le numéro de sécurité sociale du patient à créer
	 * @param nomPatient Le nom du patient à créer
	 * @param prenomPatient Le prénom du patient à créer
	 * @param mdpPatient Le mot de passe du patient à créer
	 */
	public Patient(long nssPatient, String nomPatient, String prenomPatient, String mdpPatient) {
		this.nssPatient = nssPatient;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.mdpPatient = mdpPatient;
		this.listeVisites = new ArrayList<>();
	}

	/**
	 * Accesseur en lecture du numéro de sécurité sociale d'un patient
	 * @return nssPatient Le numéro de sécurité sociale du patient
	 */
	public long getNssPatient() {
		return nssPatient;
	}

	/**
	 * Accesseur en écriture du numéro de sécurité sociale d'un patient
	 * @param nssPatient Nouvelle valeur du numéro de sécurité sociale du patient
	 */
	public void setNssPatient(long nssPatient) {
		this.nssPatient = nssPatient;
	}

	/**
	 * Accesseur en lecture du nom d'un patient
	 * @return nomPatient Le nom du patient
	 */
	public String getNomPatient() {
		return nomPatient;
	}

	/**
	 * Accesseur en écriture du nom d'un patient
	 * @param nomPatient Nouvelle valeur du nom du patient
	 */
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	/**
	 * Accesseur en lecture du prénom d'un patient
	 * @return prenomPatient Le prénom du patient
	 */
	public String getPrenomPatient() {
		return prenomPatient;
	}

	/**
	 * Accesseur en écriture du prénom d'un patient
	 * @param prenomPatient Nouvelle valeur du prénom du patient
	 */
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	/**
	 * Accesseur en lecture du mot de passe d'un patient
	 * @return mdpPatient Le mot de passe du patient
	 */
	public String getMdpPatient() {
		return mdpPatient;
	}

	/**
	 * Accesseur en écriture du mot de passe d'un patient
	 * @param mdpPatient Nouvelle valeur du mot de passe du patient
	 */
	public void setMdpPatient(String mdpPatient) {
		this.mdpPatient = mdpPatient;
	}

	/**
	 * Accesseur en lecture de la liste des visites d'un patient
	 * @return listeVisites La liste des visites d'un patient
	 */
	public List<Visite> getListeVisites() {
		return listeVisites;
	}

	/**
	 * Accesseur en écriture de la liste des visites d'un patient
	 * @param listeVisites Nouvelle valeur de la liste des visites d'un patient
	 */
	public void setListeVisites(List<Visite> listeVisites) {
		this.listeVisites = listeVisites;
	}

	/**
	 * Méthode permettant d'ajouter une visite à la liste des visites d'un patient
	 * @param visite La visite à ajouter à la liste des visites d'un patient
	 */
	public void ajouterAListeVisites(Visite visite) {
		this.listeVisites.add(visite);
	}

	
	/**
	 * Méthode permettant d'obtenir une chaine de caractères représentant un patient
	 */
	@Override
	public String toString() {
		return "Patient [nssPatient=" + nssPatient + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient
				+ ", mdpPatient=" + mdpPatient + "]";
	}

	/**
	 * Méthode permettant de vérifier si les identifiants d'un patient sont valides ou non
	 * @param nss Le numéro de sécurité sociale du patient
	 * @param mdp Le mot de passe du patient
	 * @return valide Booléen indiquant si les identifiants sont valides ou non
	 */
	public static boolean verificationIdentifiants(long nss, String mdp) {
		// Requête permettant de récupérer les identifiants du patient dans la base de données si ils existent
		String strRequete = "SELECT nssPatient FROM Patient WHERE nssPatient = :nss AND mdpPatient = :mdp";
		// Ouverture d'une session et d'une transaction hibernate
		DatabaseLink.open();
		// Création d'un objet de type Query permettant de stocker la requête
		Query requete = DatabaseLink.getSession().createQuery(strRequete);
		// Attribution de la valeur à la variable de la requête
		requete.setParameter("nss", nss);
		requete.setParameter("mdp", mdp);
		// Exécution de la requête et stockage du résultat dans une liste d'objets
		List<Patient> listeResultat = requete.getResultList();
		// Fermeture de la session et de la transaction hibernate
		DatabaseLink.close();
		// Booléen indiquant si les identifiants sont valides ou non
		boolean valide = false;
		// Si la liste des résultats est vide, les identifiants ne sont pas valides, sinon ils le sont
		if (listeResultat.isEmpty()) {
			valide = false;
		} else {
			valide = true;
		}
		return valide;
	}
}
