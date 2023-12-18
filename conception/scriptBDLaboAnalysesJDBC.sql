CREATE TABLE IF NOT EXISTS Patient(
   nssPatient BIGINT NOT NULL,
   nomPatient VARCHAR(50),
   prenomPatient VARCHAR(50),
   mdpPatient VARCHAR(50),
   PRIMARY KEY(nssPatient)
);

CREATE TABLE IF NOT EXISTS Analyse(
   idAnalyse INT NOT NULL AUTO_INCREMENT,
   nomAnalyse VARCHAR(50),
   prixAnalyse DECIMAL(15,2),
   PRIMARY KEY(idAnalyse)
);

CREATE TABLE IF NOT EXISTS Medecin(
   nssMedecin BIGINT NOT NULL,
   nomMedecin VARCHAR(50),
   prenomMedecin VARCHAR(50),
   salaireMedecin DECIMAL(15,2),
   PRIMARY KEY(nssMedecin)
);

CREATE TABLE IF NOT EXISTS Visite(
   idVisite INT NOT NULL AUTO_INCREMENT,
   dateVisite DATE,
   heureVisite TIME,
   idAnalyse INT,
   nssPatient BIGINT,
   PRIMARY KEY(idVisite),
   FOREIGN KEY(idAnalyse) REFERENCES Analyse(idAnalyse),
   FOREIGN KEY(nssPatient) REFERENCES Patient(nssPatient)
);

CREATE TABLE IF NOT EXISTS Paiement(
   idPaiement INT NOT NULL AUTO_INCREMENT,
   numCarteBancaire BIGINT,
   cvvCarteBancaire INT,
   expCarteBancaire DATE,
   idVisite INT NOT NULL,
   PRIMARY KEY(idPaiement),
   UNIQUE(idVisite),
   FOREIGN KEY(idVisite) REFERENCES Visite(idVisite)
);

CREATE TABLE IF NOT EXISTS Est_autorise(
   idAnalyse INT NOT NULL,
   nssMedecin BIGINT NOT NULL,
   PRIMARY KEY(idAnalyse, nssMedecin),
   FOREIGN KEY(idAnalyse) REFERENCES Analyse(idAnalyse),
   FOREIGN KEY(nssMedecin) REFERENCES Medecin(nssMedecin)
);
