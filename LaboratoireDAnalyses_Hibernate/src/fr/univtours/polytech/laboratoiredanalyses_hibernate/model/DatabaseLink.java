package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * @author Dorian GILBERT
 *
 */

/**
 * Classe permettant de gérer les opérations liées à la base de données
 */
public class DatabaseLink {
	/**
	 * Attribut privé représentant la session Hibernate
	 */
	private static Session session = null;
	
	/**
	 * Attribut privé représentant la sessionFactory Hibernate
	 */
	private static SessionFactory sessFact = null;
	
	/**
	 * Attribut privé représentant la transaction Hibernate
	 */
	private static Transaction tr = null;

	/**
	 * Accesseur en lecture de la session Hibernate
	 * @return session La session Hibernate
	 */
	public static Session getSession() {
		return session;
	}

	/**
	 * Accesseur en écriture de la session Hibernate
	 * @param session Nouvelle valeur de la session Hibernate
	 */
	public static void setSession(Session session) {
		DatabaseLink.session = session;
	}

	/**
	 * Accesseur en lecture de la sessionFactory Hibernate
	 * @return sessFact La sessionFactory Hibernate
	 */
	public static SessionFactory getSessFact() {
		return sessFact;
	}

	/**
	 * Accesseur en écriture de la sessionFactory Hibernate
	 * @param sessFact Nouvelle valeur de la sessionFactory Hibernate
	 */
	public static void setSessFact(SessionFactory sessFact) {
		DatabaseLink.sessFact = sessFact;
	}

	/**
	 * Accesseur en lecture de la transaction Hibernate
	 * @return tr La transaction Hibernate
	 */
	public static Transaction getTr() {
		return tr;
	}

	/**
	 * Accesseur en écriture de la transaction Hibernate
	 * @param tr Nouvelle valeur de la transaction Hibernate
	 */
	public static void setTr(Transaction tr) {
		DatabaseLink.tr = tr;
	}

	/**
	 * Méthode publique permettant de se connecter à la base de données
	 */
	public static void connect() {
		// Ouverture de la session Hibernate
		sessFact = HibernateUtil.getSessionFactory();
	}
	
	/**
	 * Méthode publique permettant de se déconnecter de la base de données
	 */
	public static void disconnect() {
		// Fermeture de la session Hibernate
		sessFact.close();
	}
	
	/**
	 * Méthode publique permettant d'ouvrir la session Hibernate
	 */
	public static void open() {
		// Stockage de la session Hibernate à l'attribut privé de la classe
		session = sessFact.getCurrentSession();
		// Ouverture de la transaction Hibernate
		tr = session.beginTransaction();
	}
	
	/**
	 * Méthode publique permettant de fermer la session Hibernate
     */
	public static void close() {
		// Fermeture de la transaction Hibernate
		tr.commit();
	}
}
