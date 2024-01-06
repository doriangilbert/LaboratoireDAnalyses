package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
}
