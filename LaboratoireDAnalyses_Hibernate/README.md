# LaboratoireDAnalyses_Hibernate

## Table des matières

- [LaboratoireDAnalyses\_Hibernate](#laboratoiredanalyses_hibernate)
  - [Table des matières](#table-des-matières)
  - [Présentation](#présentation)
    - [Contenu](#contenu)
  - [Conception](#conception)
    - [Modèle conceptuel de données](#modèle-conceptuel-de-données)
    - [Modèle logique de données](#modèle-logique-de-données)
    - [Diagramme de classes](#diagramme-de-classes)
  - [Manuel de l'utilisateur](#manuel-de-lutilisateur)
    - [Outils nécessaires](#outils-nécessaires)
    - [Installation](#installation)
    - [Utilisation](#utilisation)
  - [Images de l'application](#images-de-lapplication)

## Présentation

Projet de programmation d'une application liée à une base de données permettant de gérer la réservation et le paiement de visites dans un laboratoire d'analyses. Projet réalisé dans le cadre du module "Lien SGBD - Langage OO" du septième semestre du cycle ingénieur de Polytech Tours.

Cette application a été développée en Java en faisant appel à Hibernate pour se connecter à une base de données MySQL ainsi qu'à JavaFX pour les interfaces graphiques.

### Contenu

Cette application permet à des patients de réserver et de payer des visites pour faire des analyses dans un laboratoire d'analyses médicales. Un patient souhaitant réserver une visite va pouvoir se connecter puis sélectionner une analyse à effectuer et l'application lui proposera le premier créneau de visite disponible qu'il pourra réserver puis payer.

## Conception

### Modèle conceptuel de données

![Modèle conceptuel de données](../conception/MCDBDLaboAnalyses.png)

### Modèle logique de données

![Modèle logique de données](../conception/MLDBDLaboAnalyses.png)

### Diagramme de classes

![Diagramme de classes](../conception/DiagrammeClassesLaboAnalyses.png)

## Manuel de l'utilisateur

### Outils nécessaires

- Eclipse IDE
- MySQL Community Server 8.1, installé en version complète et configuré avec MySQL Configurator en indiquant "root" en mot de passe root
- MySQL Workbench 8

### Installation

1. Ouvrir MySQL Workbench et créer une nouvelle connexion (nommée "tp" par exemple)
2. Aller dans la connexion créée et ajouter une nouvelle base de données nommée "laboratoiredanalyses"
3. Ouvrir le projet dans eclipse et lancer Main.java
4. La base de données est automatiquement remplie au lancement du programme et la fenêtre du programme s'ouvre. (Il est possible de consulter le contenu de la base de données dans MySQL Workbench)

### Utilisation

1. Saisir le numéro de sécurité sociale et le mot de passe du patient voulant réserver et payer une visite (le patient doit être déjà enregistré dans la base)
2. Cliquer sur le bouton de connexion et si les identifiants sont valides, la page de réservation s'affiche
3. Sélectionner l'analyse à effectuer, la date et l'heure de la première visite disponible pour cette analyse s'affichent si une visite est disponible
4. Cliquer sur le bouton de réservation pour valider, la page de paiement s'affiche, ou cliquer sur le bouton de retour à l'accueil pour annuler
5. Le montant à régler s'affiche, saisir les informations de paiement que sont le numéro de la carte bancaire, son cryptogramme visuel ainsi que son mois et son année d'expiration
6. Cliquer sur le bouton de paiement pour valider ou sur le bouton de retour à l'accueil pour annuler
7. Si toutes les informations sont valides, le paiement est validé et la visite est réservée.

## Images de l'application

![Page d'accueil](../images/PageAccueil.png)
![Page de réservation](../images/Reservation.png)
![Page de paiement](../images/Paiement.png)
