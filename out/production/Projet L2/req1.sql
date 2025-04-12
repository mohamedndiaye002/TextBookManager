CREATE DATABASE cdt;
USE cdt ;




-- -----------------------------------------------------
-- Table Personnel
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Personnel (
  idPersonnel INT NOT NULL AUTO_INCREMENT AUTO_INCREMENT,
  firstName VARCHAR(45) NOT NULL,
  lastName VARCHAR(45) NULL,
  telephone VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  signature BLOB NOT NULL,
  PRIMARY KEY (idPersonnel),
  UNIQUE INDEX idPersonnel_UNIQUE (idPersonnel),
  UNIQUE INDEX email_UNIQUE (email),
  UNIQUE INDEX telephone_UNIQUE (telephone),
  UNIQUE INDEX signature_UNIQUE (signature));


-- -----------------------------------------------------
-- Table Departement
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Departement (
  idDepartement INT NOT NULL,
  nomDepartement VARCHAR(45) NOT NULL,
  PRIMARY KEY (idDepartement),
  UNIQUE INDEX nomDepartement_UNIQUE (nomDepartement));


-- -----------------------------------------------------
-- Table Classe
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Classe (
  idClasse INT NOT NULL AUTO_INCREMENT,
  classeName VARCHAR(45) NOT NULL,
  idDepartement INT NOT NULL,
  UNIQUE INDEX idClasse_UNIQUE (idClasse),
  PRIMARY KEY (idClasse),
  UNIQUE INDEX classeName_UNIQUE (classeName),
  INDEX fk_Classe_Departement1_idx (idDepartement),
  CONSTRAINT fk_Classe_Departement1
    FOREIGN KEY (idDepartement)
    REFERENCES Departement (idDepartement)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table Responsable
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Responsable (
  idPersonnel INT NOT NULL,
  NCE INT NOT NULL,
  classe VARCHAR(45) NOT NULL,
  idClasse INT NOT NULL,
  UNIQUE INDEX NCE_UNIQUE (NCE),
  INDEX fk_Etudiant_Personnel1_idx (idPersonnel),
  PRIMARY KEY (idPersonnel),
  INDEX fk_Etudiant_Classe1_idx (idClasse),
  CONSTRAINT fk_Etudiant_Personnel1
    FOREIGN KEY (idPersonnel)
    REFERENCES Personnel (idPersonnel)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Etudiant_Classe1
    FOREIGN KEY (idClasse)
    REFERENCES Classe (idClasse)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table Enseignant
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Enseignant (
  idPersonnel INT NOT NULL,
  specialite VARCHAR(45) NOT NULL,
  INDEX fk_Enseignant_Personnel1_idx (idPersonnel),
  PRIMARY KEY (idPersonnel),
  CONSTRAINT fk_Enseignant_Personnel1
    FOREIGN KEY (idPersonnel)
    REFERENCES Personnel (idPersonnel)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table chefDepartment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS chefDepartment (
  idPersonnel INT NOT NULL,
  Departement_idDepartement INT NOT NULL,
  PRIMARY KEY (idPersonnel),
  INDEX fk_chefDepartment_Departement_idx (Departement_idDepartement),
  INDEX fk_chefDepartment_Personnel1_idx (idPersonnel),
  CONSTRAINT fk_chefDepartment_Departement
    FOREIGN KEY (Departement_idDepartement)
    REFERENCES Departement (idDepartement)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_chefDepartment_Personnel1
    FOREIGN KEY (idPersonnel)
    REFERENCES Personnel (idPersonnel)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table Cours
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Cours (
  idCours INT UNSIGNED NOT NULL,
  intitule VARCHAR(45) NOT NULL,
  Description TEXT NULL,
  idPersonnel INT NOT NULL,
  PRIMARY KEY (idCours),
  UNIQUE INDEX idCours_UNIQUE (idCours),
  UNIQUE INDEX intitule_UNIQUE (intitule),
  INDEX fk_Cours_Enseignant1_idx (idPersonnel),
  CONSTRAINT fk_Cours_Enseignant1
    FOREIGN KEY (idPersonnel)
    REFERENCES Enseignant (idPersonnel)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table CahierDeTexte
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CahierDeTexte (
  idCahierDeTexte INT NOT NULL,
  idClasse INT NOT NULL,
  PRIMARY KEY (idCahierDeTexte),
  UNIQUE INDEX idCahierDeTexte_UNIQUE (idCahierDeTexte),
  INDEX fk_CahierDeTexte_Classe1_idx (idClasse),
  CONSTRAINT fk_CahierDeTexte_Classe1
    FOREIGN KEY (idClasse)
    REFERENCES Classe (idClasse)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table FicheCours
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS FicheCours (
  idFiche INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  isSigned TINYINT NULL,
  details TEXT NULL,
  idCours INT UNSIGNED NOT NULL,
  idCahierDeTexte INT NOT NULL,
  INDEX fk_FicheCours_Cours1_idx (idCours),
  INDEX fk_FicheCours_CahierDeTexte1_idx (idCahierDeTexte),
  CONSTRAINT fk_FicheCours_Cours1
    FOREIGN KEY (idCours)
    REFERENCES Cours (idCours)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_FicheCours_CahierDeTexte1
    FOREIGN KEY (idCahierDeTexte)
    REFERENCES CahierDeTexte (idCahierDeTexte)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


INSERT INTO cahierdetexte(idClasse)
VALUES
(1),
(2),
(3),
(4),
(5),
(6);


INSERT INTO fichecours (isSigned, details, idCours, idCahierDeTexte) 
VALUES 
(1, 'Séance 1 : Introduction aux Mathématiques', 1, 1),
(1, 'Séance 2 : Révisions des concepts fondamentaux', 2, 2),
(0, 'Séance 3 : Exercices pratiques sur les équations', 3, 3),
(1, 'Séance 4 : Théorie des ensembles', 4, 4),
(0, 'Séance 5 : Approfondissement sur les fonctions', 5, 5),
(1, 'Séance 6 : Étude des limites et continuité', 6, 6),
(1, 'Séance 7 : Séries et suites numériques', 7, 1),
(0, 'Séance 8 : Introduction à l\'algèbre linéaire', 8, 2),
(1, 'Séance 9 : Matrices et déterminants', 9, 3),
(1, 'Séance 10 : Résolution de systèmes d\'équations', 10, 4),
(0, 'Séance 11 : Calcul différentiel', 11, 5),
(1, 'Séance 12 : Dérivées et applications', 12, 6),
(1, 'Séance 13 : Intégration simple', 13, 1),
(1, 'Séance 14 : Applications de l\'intégration', 14, 2),
(0, 'Séance 15 : Étude de probabilités', 15, 3),
(1, 'Séance 16 : Distribution des probabilités', 16, 4),
(0, 'Séance 17 : Statistiques descriptives', 17, 5),
(1, 'Séance 18 : Étude de cas pratique en statistiques', 18, 6),
(1, 'Séance 19 : Calculs avancés en probabilités', 19, 1),
(1, 'Séance 20 : Introduction aux mathématiques financières', 20, 2),
(1, 'Séance 21 : Bases des matrices de transition', 1, 3),
(0, 'Séance 22 : Interpolation et approximation', 2, 4),
(1, 'Séance 23 : Techniques avancées d\'intégration', 3, 5),
(0, 'Séance 24 : Analyse et traitement de données', 4, 6),
(1, 'Séance 25 : Introduction aux statistiques inférentielles', 5, 1),
(1, 'Séance 26 : Exemples de tests statistiques', 6, 2),
(0, 'Séance 27 : Révisions des concepts clés', 7, 3),
(1, 'Séance 28 : Étude de corrélation et régression', 8, 4),
(1, 'Séance 29 : Projets mathématiques collaboratifs', 9, 5),
(1, 'Séance 30 : Finalisation et discussions', 10, 6);


-- SELECT * FROM fichecours WHERE isSigned =False;


SELECT cl.`classeName`, c.intitule FROM personnel p INNER JOIN cours c ON p.`idPersonnel` = c.`idPersonnel`
INNER JOIN fichecours f ON f.`idCours` = c.`idCours`
INNER JOIN cahierdetexte ct ON f.`idCahierDeTexte` = ct.`idCahierDeTexte`
INNER JOIN classe cl ON ct.`idClasse`= cl.`idClasse`
WHERE cl.`idDepartement` =1 AND p.`idPersonnel` = 21;



SELECT * FROM cours ;

SELECT DISTINCT cdt.idcahierdetexte FROM fichecours f INNER JOIN cours c ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE cl.`classeName` = "LSEE1";



UPDATE fichecours SET `isSigned`=1 WHERE `idFiche`=31;


SELECT f.* FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =16;


SELECT DISTINCT cl.classeName, c.intitule FROM enseignant e INNER JOIN cours c ON e.idPersonnel = c.idPersonnel INNER JOIN fichecours f ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE e.idPersonnel = 21 ORDER BY cl.classeName ASC;

UPDATE fichecours
SET `isSigned` = 1 WHERE `idFiche` = 35;


SELECT* FROM fichecours WHERE `isSigned`=1;



UPDATE personnel
SET `is` = 3 WHERE `idPersonnel` BETWEEN 0 AND 20;

SELECT * FROM personnel;

SELECT * FROM personnel p INNER JOIN enseignant e ON p.`idPersonnel` = e.`idPersonnel`;



SELECT * FROM personnel p INNER JOIN responsable r ON p.idPersonnel = r.idPersonnel INNER JOIN classe c ON c.idClasse = r.idClasse ORDER BY p.idPersonnel;

SELECT * FROM personnel p INNER JOIN responsable r ON p.idPersonnel = r.idPersonnel INNER JOIN classe c ON c.idClasse = r.idClasse WHERE p.`idPersonnel` =3;


SELECT DISTINCT cl.classeName, c.intitule FROM enseignant e INNER JOIN cours c ON e.idPersonnel = c.idPersonnel INNER JOIN fichecours f ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE e.idPersonnel = 25 ORDER BY cl.classeName ASC;