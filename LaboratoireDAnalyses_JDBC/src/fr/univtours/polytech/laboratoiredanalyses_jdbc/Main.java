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
			Visite visite2 = new Visite(LocalDateTime.of(2024, 1, 3, 14, 0), analyse3);
			Visite visite3 = new Visite(LocalDateTime.of(2024, 1, 11, 17, 15), analyse1);
			Visite visite4 = new Visite(LocalDateTime.of(2024, 1, 25, 13, 30), analyse1);
			Visite visite5 = new Visite(LocalDateTime.of(2024, 1, 25, 15, 20), analyse3);
			Visite visite6 = new Visite(LocalDateTime.of(2024, 2, 6, 8, 15), analyse2);
			Visite visite7 = new Visite(LocalDateTime.of(2024, 2, 6, 9, 45), analyse2);
			
			visite1.setPatient(patient2);
			Paiement paiement1 = new Paiement(1234432123144123L, 852, LocalDate.of(2027, 8, 1), visite1);
			
			medecin1.autoriserAnalyse(analyse2);
			medecin2.autoriserAnalyse(analyse1);
			medecin2.autoriserAnalyse(analyse3);
			
			//Création du Scanner qui va collecter les entrées de l'utilisateur
			Scanner scanner = new Scanner(System.in);
			long saisieNss = 0;
			System.out.print("Saisissez votre numero de sécurité sociale : ");
			saisieNss = scanner.nextLong();
			String saisieMdp = "";
			System.out.print("Saisissez votre mot de passe : ");
			saisieMdp = scanner.next();
			if (Patient.verificationIdentifiants(saisieNss, saisieMdp)) {
				System.out.println("Connecté.");
				Analyse.afficherListeAnalyses();
				int saisieIdAnalyse = 0;
				System.out.print("Saisissez le numéro correspondant à l'analyse que vous souhaitez réserver : ");
				saisieIdAnalyse = scanner.nextInt();
				int idVisite = Visite.afficherPremiereVisiteDisponible(saisieIdAnalyse);
				if(idVisite != 0) {
					String saisieDemandeReservationVisite = "";
					System.out.println("Souhaitez-vous réserver cette visite ? (Saisir 'O' pour résever la visite ou 'N' pour annuler la réservation)");
					saisieDemandeReservationVisite = scanner.next();
					if(saisieDemandeReservationVisite.equals("O") || saisieDemandeReservationVisite.equals("o")) {
						if(Visite.reserverVisite(idVisite, saisieNss)) {
							System.out.println("Visite réservée.");
							long saisieNumCarteBancaire = 0;
							System.out.print("Saisissez votre numéro de carte bancaire : ");
							saisieNumCarteBancaire = scanner.nextLong();
							int saisieCvvCarteBancaire = 0;
							System.out.print("Saisissez le CVV de votre carte bancaire : ");
							saisieCvvCarteBancaire = scanner.nextInt();
							int saisieMoisExpCarteBancaire = 0;
							System.out.print("Saisissez le mois d'expiration de votre carte bancaire : ");
							saisieMoisExpCarteBancaire = scanner.nextInt();
							if (saisieMoisExpCarteBancaire >= 1 || saisieMoisExpCarteBancaire <= 12) {
								int saisieAnneeExpCarteBancaire = 0;
								System.out.print("Saisissez l'année d'expiration de votre carte bancaire : ");
								saisieAnneeExpCarteBancaire = scanner.nextInt();
								if (saisieAnneeExpCarteBancaire >= 2000 || saisieAnneeExpCarteBancaire >= LocalDate.now().getYear()) {
									LocalDate saisieExpCarteBancaire = LocalDate.of(saisieAnneeExpCarteBancaire, saisieMoisExpCarteBancaire, 1);
									if (saisieExpCarteBancaire.isAfter(LocalDate.now())) {
										System.out.println("Paiement validé.");
										Visite.payerVisite(idVisite, saisieNumCarteBancaire, saisieCvvCarteBancaire, saisieExpCarteBancaire);
									} else {
										System.out.println("Carte bancaire expirée.");
									}
								} else {
									System.out.println("Année incorrecte.");
								}
							} else {
								System.out.println("Mois incorrect.");
							}
						}
					}
				}
			}
			System.out.println("Merci, bonne journée.");
			//Libération des ressources liées au scanner
			scanner.close();
			
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex);
			System.exit(1);
		}
	}
}
