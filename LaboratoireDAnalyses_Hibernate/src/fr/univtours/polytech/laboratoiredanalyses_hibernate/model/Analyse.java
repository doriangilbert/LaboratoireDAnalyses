package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Dorian GILBERT
 *
 */

/**
 * Classe représentant une analyse
 */
@Entity
@Table(name = "Analyse")
public class Analyse {
	
	/**
	 * Attribut privé représentant l'identifiant d'une analyse
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAnalyse")
	private int idAnalyse;

	/**
	 * Attribut privé représentant le nom d'une analyse
	 */
	@Column(name = "nomAnalyse")
	private String nomAnalyse;

	/**
	 * Attribut privé représentant le prix d'une analyse
	 */
	@Column(name = "prixAnalyse")
	private float prixAnalyse;

	/**
	 * Attribut privé représentant la liste des médecins autorisés à effectuer une analyse
	 */
	@ManyToMany(mappedBy = "listeAnalyses")
	private List<Medecin> listeMedecins;

	/**
	 * Constructeur par défaut d'une analyse
	 */
	public Analyse() {
		this.listeMedecins = new ArrayList<>();
	}
	
	/**
	 * Constructeur d'une analyse
	 * @param nomAnalyse Le nom de l'analyse à créer
	 * @param prixAnalyse Le prix de l'analyse à créer
	 */
	public Analyse(String nomAnalyse, float prixAnalyse) {
		this.nomAnalyse = nomAnalyse;
		this.prixAnalyse = prixAnalyse;
		this.listeMedecins = new ArrayList<>();
	}

	/**
	 * Accesseur en lecture de l'identifiant d'une analyse
	 * @return idAnalyse L'identifiant de l'analyse
	 */
	public int getIdAnalyse() {
		return idAnalyse;
	}

	/**
	 * Accesseur en écriture de l'identifiant d'une analyse
	 * @param idAnalyse Nouvelle valeur de l'identifiant de l'analyse
	 */
	public void setIdAnalyse(int idAnalyse) {
		this.idAnalyse = idAnalyse;
	}

	/**
	 * Accesseur en lecture du nom d'une analyse
	 * @return nomAnalyse Le nom de l'analyse
	 */
	public String getNomAnalyse() {
		return nomAnalyse;
	}

	/**
	 * Accesseur en écriture du nom d'une analyse
	 * @param nomAnalyse Nouvelle valeur du nom de l'analyse
	 */
	public void setNomAnalyse(String nomAnalyse) {
		this.nomAnalyse = nomAnalyse;
	}

	/**
	 * Accesseur en lecture du prix d'une analyse
	 * @return prixAnalyse Le prix de l'analyse
	 */
	public float getPrixAnalyse() {
		return prixAnalyse;
	}

	/**
	 * Accesseur en écriture du prix d'une analyse
	 * @param prixAnalyse Nouvelle valeur du prix de l'analyse
	 */
	public void setPrixAnalyse(float prixAnalyse) {
		this.prixAnalyse = prixAnalyse;
	}

	/**
	 * Accesseur en lecture de la liste des médecins autorisés à effectuer une analyse
	 * @return listeMedecins La liste des médecins autorisés à effectuer une analyse
	 */
	public List<Medecin> getListeMedecins() {
		return listeMedecins;
	}

	/**
	 * Accesseur en écriture de la liste des médecins autorisés à effectuer une analyse
	 * @param listeMedecins Nouvelle valeur de la liste des médecins autorisés à effectuer une analyse
	 */
	public void setListeMedecins(List<Medecin> listeMedecins) {
		this.listeMedecins = listeMedecins;
	}

	/**
	 * Méthode permettant d'ajouter un médecin à la liste des médecins autorisés à effectuer une analyse
	 * @param medecin Le médecin à ajouter à la liste des médecins autorisés à effectuer une analyse
	 */
	public void ajouterAListeMedecins(Medecin medecin) {
		this.listeMedecins.add(medecin);
	}

	/**
	 * Méthode permettant d'obtenir une chaine de caractères représentant une analyse
	 */
	@Override
	public String toString() {
		return "Analyse [idAnalyse=" + idAnalyse + ", nomAnalyse=" + nomAnalyse + ", prixAnalyse=" + prixAnalyse + "]";
	}
}
