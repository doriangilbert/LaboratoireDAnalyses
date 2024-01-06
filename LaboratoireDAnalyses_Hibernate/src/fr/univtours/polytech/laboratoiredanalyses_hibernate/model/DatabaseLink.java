package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * @author Dorian GILBERT
 *
 */

public class DatabaseLink {
	// Attribut privé de type Session permettant de stocker la session Hibernate
	private static Session session = null;
	
	private static SessionFactory sessFact = null;
	
	private static Transaction tr = null;

	/**
	 * @return the session
	 */
	public static Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public static void setSession(Session session) {
		DatabaseLink.session = session;
	}

	/**
	 * @return the sessFact
	 */
	public static SessionFactory getSessFact() {
		return sessFact;
	}

	/**
	 * @param sessFact the sessFact to set
	 */
	public static void setSessFact(SessionFactory sessFact) {
		DatabaseLink.sessFact = sessFact;
	}

	/**
	 * @return the tr
	 */
	public static Transaction getTr() {
		return tr;
	}

	/**
	 * @param tr the tr to set
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
	
	public static void open() {
		// Stockage de la session Hibernate à l'attribut privé de la classe
		session = sessFact.getCurrentSession();
		// Ouverture de la transaction Hibernate
		tr = session.beginTransaction();
	}
	
	public static void close() {
		// Fermeture de la transaction Hibernate
		tr.commit();
	}
}
