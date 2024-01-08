package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */

/**
 * Classe représentant un médecin
 */
@Entity
@Table(name = "Medecin")
public class Medecin {
	
	/**
	 * Attribut privé représentant le numéro de sécurité sociale d'un médecin
	 */
	@Id
	@Column(name = "nssMedecin")
	private long nssMedecin;

	/**
	 * Attribut privé représentant le nom d'un médecin
	 */
	@Column(name = "nomMedecin")
	private String nomMedecin;

	/**
	 * Attribut privé représentant le prénom d'un médecin
	 */
	@Column(name = "prenomMedecin")
	private String prenomMedecin;
	
	/**
	 * Attribut privé représentant le salaire d'un médecin
	 */
	@Column(name = "salaireMedecin")
	private float salaireMedecin;

	/**
	 * Attribut privé représentant la liste des analyses autorisées à être effectuées par un médecin
	 */
	@ManyToMany
	@JoinTable(name = "Est_autorise", joinColumns = @JoinColumn(name = "nssMedecin"), inverseJoinColumns = @JoinColumn(name = "idAnalyse"))
	private List<Analyse> listeAnalyses;

	/**
	 * Constructeur par défaut d'un médecin
	 */
	public Medecin() {
		this.listeAnalyses = new ArrayList<>();
	}
	
	/**
	 * Constructeur d'un médecin
	 * @param nssMedecin Le numéro de sécurité sociale du médecin à créer
	 * @param nomMedecin Le nom du médecin à créer
	 * @param prenomMedecin Le prénom du médecin à créer
	 * @param salaireMedecin Le salaire du médecin à créer
	 */
	public Medecin(long nssMedecin, String nomMedecin, String prenomMedecin, float salaireMedecin) {
		this.nssMedecin = nssMedecin;
		this.nomMedecin = nomMedecin;
		this.prenomMedecin = prenomMedecin;
		this.salaireMedecin = salaireMedecin;
		this.listeAnalyses = new ArrayList<>();
	}

	/**
	 * Accesseur en lecture du numéro de sécurité sociale d'un médecin
	 * @return nssMedecin Le numéro de sécurité sociale du médecin
	 */
	public long getNssMedecin() {
		return nssMedecin;
	}

	/**
	 * Accesseur en écriture du numéro de sécurité sociale d'un médecin
	 * @param nssMedecin Nouvelle valeur du numéro de sécurité sociale du médecin
	 */
	public void setNssMedecin(long nssMedecin) {
		this.nssMedecin = nssMedecin;
	}

	/**
	 * Accesseur en lecture du nom d'un médecin
	 * @return nomMedecin Le nom du médecin
	 */
	public String getNomMedecin() {
		return nomMedecin;
	}

	/**
	 * Accesseur en écriture du nom d'un médecin
	 * @param nomMedecin Nouvelle valeur du nom du médecin
	 */
	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	/**
	 * Accesseur en lecture du prénom d'un médecin
	 * @return prenomMedecin Le prénom du médecin
	 */
	public String getPrenomMedecin() {
		return prenomMedecin;
	}

	/**
	 * Accesseur en écriture du prénom d'un médecin
	 * @param prenomMedecin Nouvelle valeur du prénom du médecin
	 */
	public void setPrenomMedecin(String prenomMedecin) {
		this.prenomMedecin = prenomMedecin;
	}

	/**
	 * Accesseur en lecture du salaire d'un médecin
	 * @return salaireMedecin Le salaire du médecin
	 */
	public float getSalaireMedecin() {
		return salaireMedecin;
	}

	/**
	 * Accesseur en écriture du salaire d'un médecin
	 * @param salaireMedecin Nouvelle valeur du salaire du médecin
	 */
	public void setSalaireMedecin(float salaireMedecin) {
		this.salaireMedecin = salaireMedecin;
	}

	/**
	 * Accesseur en lecture de la liste des analyses autorisées à être effectuées par un médecin
	 * @return listeAnalyses La liste des analyses autorisées à être effectuées par un médecin
	 */
	public List<Analyse> getListeAnalyses() {
		return listeAnalyses;
	}

	/**
	 * Accesseur en écriture de la liste des analyses autorisées à être effectuées par un médecin
	 * @param listeAnalyses Nouvelle valeur de la liste des analyses autorisées à être effectuées par un médecin
	 */
	public void setListeAnalyses(List<Analyse> listeAnalyses) {
		this.listeAnalyses = listeAnalyses;
	}

	/**
	 * Méthode permettant d'ajouter une analyse à la liste des analyses autorisées à être effectuées par un médecin
	 * @param analyse L'analyse à ajouter à la liste des analyses autorisées à être effectuées par un médecin
	 */
	public void ajouterAListeAnalyses(Analyse analyse) {
		this.listeAnalyses.add(analyse);
	}

	/**
	 * Méthode permettant d'obtenir une chaine de caractères représentant un médecin
	 */
	@Override
	public String toString() {
		return "Medecin [nssMedecin=" + nssMedecin + ", nomMedecin=" + nomMedecin + ", prenomMedecin=" + prenomMedecin
				+ ", salaireMedecin=" + salaireMedecin + "]";
	}
	
	/**
	 * Méthode permettant d'autoriser un médecin à effectuer une analyse
	 * @param analyse L'analyse à autoriser à être effectuée par un médecin
	 */
	public void autoriserAnalyse(Analyse analyse) {
		this.listeAnalyses.add(analyse);
		analyse.getListeMedecins().add(this);
	}
}
