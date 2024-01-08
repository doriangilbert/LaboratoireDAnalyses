package fr.univtours.polytech.laboratoiredanalyses_hibernate.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author GILBERT Dorian
 * 
 */

/**
 * Classe permettant de gérer les opérations liées à Hibernate
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration conf = new Configuration().configure();
			conf.addAnnotatedClass(fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Analyse.class);
			conf.addAnnotatedClass(fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Medecin.class);
			conf.addAnnotatedClass(fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Paiement.class);
			conf.addAnnotatedClass(fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Patient.class);
			conf.addAnnotatedClass(fr.univtours.polytech.laboratoiredanalyses_hibernate.model.Visite.class);
			conf.configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			sessionFactory = conf.buildSessionFactory(sr);
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
