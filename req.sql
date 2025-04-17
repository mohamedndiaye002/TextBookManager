CREATE DATABASE CahierDeTexte;

USE CahierDeTexte;

-- -----------------------------------------------------
-- Table Personnel
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Personnel (
    idPersonnel INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NULL,
    telephone VARCHAR(45) NULL,
    email VARCHAR(45) NULL,
    signature BLOB NOT NULL,
    PRIMARY KEY (idPersonnel),
    UNIQUE INDEX idPersonnel_UNIQUE (idPersonnel ASC),
    UNIQUE INDEX email_UNIQUE (email ASC),
    UNIQUE INDEX telephone_UNIQUE (telephone ASC),
    UNIQUE INDEX signature_UNIQUE (signature ASC)
);

DESCRIBE Personnel;

-- -----------------------------------------------------
-- Insert data into Personnel
-- -----------------------------------------------------
INSERT INTO
    Personnel (
        firstName,
        lastName,
        telephone,
        email,
        signature
    )
VALUES (
        'Mamadou',
        'Diop',
        '771234567',
        'mamadou.diop@gmail.com',
        'signature1'
    ),
    (
        'Awa',
        'Ndiaye',
        '778765432',
        'awa.ndiaye@gmail.com',
        'signature2'
    ),
    (
        'Cheikh',
        'Ba',
        '776543210',
        'cheikh.ba@gmail.com',
        'signature3'
    ),
    (
        'Fatou',
        'Sow',
        '775678901',
        'fatou.sow@gmail.com',
        'signature4'
    ),
    (
        'Ibrahima',
        'Fall',
        '774321098',
        'ibrahima.fall@gmail.com',
        'signature5'
    ),
    (
        'Mariama',
        'Faye',
        '773210987',
        'mariama.faye@gmail.com',
        'signature6'
    ),
    (
        'Ousmane',
        'Sy',
        '772109876',
        'ousmane.sy@gmail.com',
        'signature7'
    ),
    (
        'Adama',
        'Gueye',
        '779876543',
        'adama.gueye@gmail.com',
        'signature8'
    ),
    (
        'Khady',
        'Diagne',
        '778901234',
        'khady.diagne@gmail.com',
        'signature9'
    ),
    (
        'Modou',
        'Kane',
        '777654321',
        'modou.kane@gmail.com',
        'signature10'
    );

-- -----------------------------------------------------
-- Insert additional data into Personnel
-- -----------------------------------------------------
INSERT INTO
    Personnel (
        firstName,
        lastName,
        telephone,
        email,
        signature
    )
VALUES (
        'Amadou',
        'Diallo',
        '770123456',
        'amadou.diallo@gmail.com',
        'signature11'
    ),
    (
        'Seynabou',
        'Diouf',
        '779876321',
        'seynabou.diouf@gmail.com',
        'signature12'
    ),
    (
        'Boubacar',
        'Camara',
        '778654321',
        'boubacar.camara@gmail.com',
        'signature13'
    ),
    (
        'Aissatou',
        'Fall',
        '771098765',
        'aissatou.fall@gmail.com',
        'signature14'
    ),
    (
        'Moussa',
        'Ndiaye',
        '772345678',
        'moussa.ndiaye@gmail.com',
        'signature15'
    ),
    (
        'Fatimata',
        'Sarr',
        '773456789',
        'fatimata.sarr@gmail.com',
        'signature16'
    ),
    (
        'Aliou',
        'Gaye',
        '774567890',
        'aliou.gaye@gmail.com',
        'signature17'
    );

-- -----------------------------------------------------
-- Insert additional data into Personnel
-- -----------------------------------------------------
INSERT INTO
    Personnel (
        firstName,
        lastName,
        telephone,
        email,
        signature
    )
VALUES (
        'Jean',
        'Dupont',
        '770987654',
        'jean.dupont@gmail.com',
        'signature18'
    ),
    (
        'Sophie',
        'Martin',
        '771234890',
        'sophie.martin@gmail.com',
        'signature19'
    ),
    (
        'Paul',
        'Durand',
        '772345901',
        'paul.durand@gmail.com',
        'signature20'
    ),
    (
        'Claire',
        'Lemoine',
        '773456012',
        'claire.lemoine@gmail.com',
        'signature21'
    );

SELECT * FROM Personnel;

-- -----------------------------------------------------
-- Table Departement
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Departement (
    idDepartement INT NOT NULL,
    nomDepartement VARCHAR(45) NOT NULL,
    PRIMARY KEY (idDepartement),
    UNIQUE INDEX nomDepartement_UNIQUE (nomDepartement ASC)
);

DESCRIBE Departement;

-- -----------------------------------------------------
-- Insert data into Departement
-- -----------------------------------------------------
INSERT INTO
    Departement (idDepartement, nomDepartement)
VALUES (200, 'Mathematique'),
    (201, 'Physique-Chimie'),
    (
        202,
        'Hydro-science et Environnement'
    ),
    (203, 'Informatique');

SELECT * FROM Departement;

-- -----------------------------------------------------
-- Table Etudiant
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Etudiant (
    idPersonnel INT NOT NULL,
    NCE INT NOT NULL UNIQUE,
    classe VARCHAR(45) NOT NULL,
    PRIMARY KEY (idPersonnel),
    FOREIGN KEY (idPersonnel) REFERENCES Personnel (idPersonnel) ON DELETE CASCADE ON UPDATE CASCADE
);

DESCRIBE Etudiant;

-- -----------------------------------------------------
-- Insert data into Etudiant
-- -----------------------------------------------------
INSERT INTO
    responsable (idPersonnel, NCE, classe)
VALUES (1, 100, 'LI2'),
    (2, 101, 'LI2'),
    (3, 102, 'LI2'),
    (4, 103, 'LI2'),
    (5, 104, 'LI2'),
    (6, 105, 'LI3'),
    (7, 106, 'LI3'),
    (8, 107, 'LI3'),
    (9, 108, 'LI3'),
    (10, 109, 'LI3');

SELECT * FROM Etudiant;

-- -----------------------------------------------------
-- Table Enseignant
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Enseignant (
    idPersonnel INT NOT NULL,
    specialite VARCHAR(45) NOT NULL,
    PRIMARY KEY (idPersonnel),
    FOREIGN KEY (idPersonnel) REFERENCES Personnel (idPersonnel) ON DELETE CASCADE ON UPDATE CASCADE
);

DESCRIBE Enseignant;

-- -----------------------------------------------------
-- Insert data into Enseignant
-- -----------------------------------------------------
INSERT INTO
    Enseignant (idPersonnel, specialite)
VALUES (11, 'Mathematique'),
    (12, 'Physique'),
    (13, 'Chimie'),
    (14, 'Informatique'),
    (15, 'Hydro-science'),
    (16, 'Environnement'),
    (17, 'Statistique');

SELECT p.* FROM Enseignant e INNER JOIN personnel p ON e.`idPersonnel` = p.`idPersonnel`;

-- -----------------------------------------------------
-- Table chefDepartment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS chefDepartment (
    idPersonnel INT NOT NULL,
    idDepartement INT NOT NULL,
    PRIMARY KEY (idPersonnel),
    FOREIGN KEY (idDepartement) REFERENCES Departement (idDepartement) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPersonnel) REFERENCES Personnel (idPersonnel) ON DELETE CASCADE ON UPDATE CASCADE
);

DESCRIBE chefdepartment;

-- -----------------------------------------------------
-- Insert data into chefDepartment
-- -----------------------------------------------------
INSERT INTO
    chefDepartment (idPersonnel, idDepartement)
VALUES (18, 200),
    (19, 201),
    (20, 202),
    (21, 203);

SELECT * FROM chefDepartment;

-- -----------------------------------------------------
-- Table Cours
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Cours (
    idCours INT UNSIGNED NOT NULL,
    intitule VARCHAR(45) NOT NULL,
    Description TEXT NULL,
    idPersonnel INT NOT NULL,
    PRIMARY KEY (idCours),
    FOREIGN KEY (idPersonnel) REFERENCES Personnel (idPersonnel) ON DELETE CASCADE ON UPDATE CASCADE
);

DESCRIBE Cours;

-- -----------------------------------------------------
-- Insert data into Cours
-- -----------------------------------------------------
INSERT INTO
    Cours (
        idCours,
        intitule,
        Description,
        idPersonnel
    )
VALUES (
        1,
        'Algebre',
        'Cours sur les structures algébriques',
        11
    ),
    (
        2,
        'Analyse',
        'Cours sur les fonctions et les limites',
        11
    ),
    (
        3,
        'Physique Generale',
        'Cours sur les principes fondamentaux de la physique',
        12
    ),
    (
        4,
        'Chimie Organique',
        'Cours sur les composés organiques',
        13
    ),
    (
        5,
        'Programmation',
        'Cours sur les bases de la programmation',
        14
    ),
    (
        6,
        'Hydrologie',
        'Cours sur le cycle de l\'eau',
        15
    ),
    (
        7,
        'Ecologie',
        'Cours sur les interactions environnementales',
        16
    ),
    (
        8,
        'Statistique Avancée',
        'Cours sur les méthodes statistiques avancées',
        17
    ),
    (
        9,
        'Geometrie',
        'Cours sur les formes et les espaces',
        11
    ),
    (
        10,
        'Thermodynamique',
        'Cours sur les lois de la thermodynamique',
        12
    ),
    (
        11,
        'Chimie Inorganique',
        'Cours sur les composés inorganiques',
        13
    ),
    (
        12,
        'Structures de Données',
        'Cours sur les structures de données avancées',
        14
    ),
    (
        13,
        'Gestion de l\'Eau',
        'Cours sur la gestion des ressources en eau',
        15
    ),
    (
        14,
        'Biodiversité',
        'Cours sur la diversité biologique',
        16
    ),
    (
        15,
        'Probabilités',
        'Cours sur les bases des probabilités',
        17
    );

SELECT * FROM Cours;

-- -----------------------------------------------------
-- Table FicheCours
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS FicheCours (
    idPersonnel INT NOT NULL,
    idCours INT UNSIGNED NOT NULL,
    idFiche INT NOT NULL,
    PRIMARY KEY (idFiche, idPersonnel, idCours),
    FOREIGN KEY (idPersonnel) REFERENCES Personnel (idPersonnel) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idCours) REFERENCES Cours (idCours) ON DELETE CASCADE ON UPDATE CASCADE
);

DESCRIBE FicheCours;

-- -----------------------------------------------------
-- Insert data into FicheCours

INSERT INTO
    FicheCours (idFiche, idPersonnel, idCours)
VALUES (1, 1, 1),
    (1, 11, 1), -- idFiche 1 is present twice
    (2, 2, 2),
    (2, 12, 2), -- idFiche 2 is present twice
    (3, 3, 3),
    (3, 13, 3), -- idFiche 3 is present twice
    (4, 1, 4),
    (4, 14, 4), -- idFiche 4 is present twice
    (5, 2, 5),
    (5, 15, 5), -- idFiche 5 is present twice
    (6, 3, 6),
    (6, 16, 6), -- idFiche 6 is present twice
    (7, 1, 7),
    (7, 17, 7), -- idFiche 7 is present twice
    (8, 2, 8),
    (8, 11, 8), -- idFiche 8 is present twice
    (9, 3, 9),
    (9, 12, 9), -- idFiche 9 is present twice
    (10, 1, 10),
    (10, 13, 10), -- idFiche 10 is present twice
    (11, 2, 11),
    (11, 14, 11), -- idFiche 11 is present twice
    (12, 3, 12),
    (12, 15, 12), -- idFiche 12 is dpresent twice
    (13, 1, 13),
    (13, 16, 13), -- idFiche 13 is present twice
    (14, 2, 14),
    (14, 17, 14), -- idFiche 14 is present twice
    (15, 2, 15);
-- idFiche 15 is present once
SELECT * FROM FicheCours;

SELECT c.*
FROM Cours c
    INNER JOIN Enseignant e ON c.idPersonnel = e.idPersonnel
WHERE
    e.idPersonnel = 11;

SELECT f.*, c.intitule
FROM
    FicheCours f
    INNER JOIN Personnel p ON f.idPersonnel = p.idPersonnel
    INNER JOIN Cours c ON f.idCours = c.idCours
ORDER BY c.idCours ASC;