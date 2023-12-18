package fr.univtours.polytech.laboratoiredanalyses_jdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author Dorian GILBERT
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			// Connexion à la base de données
			DatabaseLink.connect("jdbc:mysql://127.0.0.1:3306/laboratoiredanalyses", "root", "root");
			// Création des tables
			DatabaseLink.creationTables();
			//Peuplement de la base
			Patient patient1 = new Patient(123456789123456L, "Alain", "TERIEUR", "password");
			Patient patient2 = new Patient(987654321987654L, "Alex", "TERIEUR", "password");
			
			Analyse analyse1 = new Analyse("Hemogramme", 50);
			Analyse analyse2 = new Analyse("Groupe sanguin", 25);
			Analyse analyse3 = new Analyse("Vitesse de sédimentation", 75);
			
			Medecin medecin1 = new Medecin(147852369147852L, "Jean", "DUPONT", 3000);
			Medecin medecin2 = new Medecin(369852147369852L, "Pierre", "JACQUES", 5500);
			
			Visite visite1 = new Visite(LocalDateTime.of(2023, 12, 5, 9, 30), analyse2);
			Visite visite2 = new Visite(LocalDateTime.of(2024, 1, 11, 14, 0), analyse3);
			Visite visite3 = new Visite(LocalDateTime.of(2024, 1, 3, 17, 15), analyse1);
			
			visite1.setPatient(patient2);
			Paiement paiement1 = new Paiement(1234432123144123L, 852, LocalDate.of(2027, 8, 31), visite1);
			
			medecin1.autoriserAnalyse(analyse2);
			medecin2.autoriserAnalyse(analyse1);
			medecin2.autoriserAnalyse(analyse3);
			
			//Création du Scanner qui va collecter les entrées de l'utilisateur
			Scanner scanner = new Scanner(System.in);
			System.out.print("Saisissez votre numero de sécurité sociale : ");
			long saisieNss = 0;
			saisieNss = scanner.nextLong();
			System.out.print("Saisissez votre mot de passe : ");
			String saisieMdp = "";
			saisieMdp = scanner.next();
			if (Patient.verificationIdentifiants(saisieNss, saisieMdp)) {
				System.out.println("Connecté.");
				Visite.afficherListeVisitesDisponibles();
			}
			//Libération des ressources liées au scanner
			scanner.close();
			
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex);
			System.exit(1);
		}
	}
}
