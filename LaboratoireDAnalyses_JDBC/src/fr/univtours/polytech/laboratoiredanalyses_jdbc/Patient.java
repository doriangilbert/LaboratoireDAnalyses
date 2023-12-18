package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorian GILBERT
 *
 */
public class Patient {

	private long nssPatient;

	private String nomPatient;

	private String prenomPatient;
	
	private String mdpPatient;

	private List<Visite> listeVisites;

	/**
	 * @param nssPatient
	 * @param nomPatient
	 * @param prenomPatient
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

	public static void createTablePatient() throws SQLException {
		System.out.println("Création de la table Patient.");
		DatabaseLink.createTable("CREATE TABLE IF NOT EXISTS Patient("
				+ "nssPatient BIGINT NOT NULL,"
				+ "nomPatient VARCHAR(50),"
				+ "prenomPatient VARCHAR(50),"
				+ "mdpPatient VARCHAR(50),"
				+ "PRIMARY KEY(nssPatient)"
				+ ")");
	}
	
	public static void insertIntoPatient() {
		// TODO
	}
}
