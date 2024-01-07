package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.sql.SQLException;
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

@Entity
@Table(name = "Analyse")
public class Analyse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAnalyse")
	private int idAnalyse;

	@Column(name = "nomAnalyse")
	private String nomAnalyse;

	@Column(name = "prixAnalyse")
	private float prixAnalyse;

	@ManyToMany(mappedBy = "listeAnalyses")
	private List<Medecin> listeMedecins;

	public Analyse() {
		this.listeMedecins = new ArrayList<>();
	}
	
	/**
	 * @param nomAnalyse
	 * @param prixAnalyse
	 * @throws SQLException
	 */
	public Analyse(String nomAnalyse, float prixAnalyse) {
		this.nomAnalyse = nomAnalyse;
		this.prixAnalyse = prixAnalyse;
		this.listeMedecins = new ArrayList<>();
	}

	/**
	 * @return the idAnalyse
	 */
	public int getIdAnalyse() {
		return idAnalyse;
	}

	/**
	 * @param idAnalyse the idAnalyse to set
	 */
	public void setIdAnalyse(int idAnalyse) {
		this.idAnalyse = idAnalyse;
	}

	/**
	 * @return the nomAnalyse
	 */
	public String getNomAnalyse() {
		return nomAnalyse;
	}

	/**
	 * @param nomAnalyse the nomAnalyse to set
	 */
	public void setNomAnalyse(String nomAnalyse) {
		this.nomAnalyse = nomAnalyse;
	}

	/**
	 * @return the prixAnalyse
	 */
	public float getPrixAnalyse() {
		return prixAnalyse;
	}

	/**
	 * @param prixAnalyse the prixAnalyse to set
	 */
	public void setPrixAnalyse(float prixAnalyse) {
		this.prixAnalyse = prixAnalyse;
	}

	/**
	 * @return the listeMedecins
	 */
	public List<Medecin> getListeMedecins() {
		return listeMedecins;
	}

	/**
	 * @param listeMedecins the listeMedecins to set
	 */
	public void setListeMedecins(List<Medecin> listeMedecins) {
		this.listeMedecins = listeMedecins;
	}

	public void ajouterAListeMedecins(Medecin medecin) {
		this.listeMedecins.add(medecin);
	}

	@Override
	public String toString() {
		return "Analyse [idAnalyse=" + idAnalyse + ", nomAnalyse=" + nomAnalyse + ", prixAnalyse=" + prixAnalyse + "]";
	}
}
