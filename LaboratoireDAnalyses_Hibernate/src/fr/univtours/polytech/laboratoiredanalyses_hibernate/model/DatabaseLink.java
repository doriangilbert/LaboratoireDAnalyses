package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * @author Dorian GILBERT
 *
 */
public class DatabaseLink {
	// Attribut privé de type Session permettant de stocker la session Hibernate
	private static Session session = null;
	
	private static SessionFactory sessFact = null;

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
	 * Méthode publique permettant de se connecter à la base de données
	 */
	public static void connect() {
		// Ouverture de la session Hibernate
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		// Stockage de la session Hibernate à l'attribut privé de la classe
		session = sessFact.getCurrentSession();
	}
}
