DROP TABLE IF EXISTS Joueur, Partie, Tour, StatJeu, Coup, TerrainType, Paysage, Domino CASCADE;

CREATE TABLE Joueur (
    idJoueur serial PRIMARY KEY,
    couleur text NOT NULL,
    ia boolean DEFAULT false,
    point int DEFAULT 0
);

CREATE TABLE Partie (
    idPartie serial PRIMARY KEY,
    nbJoueurs int DEFAULT 0,
    nbIAs int DEFAULT 0,
    dernierJoueur int REFERENCES Joueur (idJoueur) NOT NULL,
    nbTour int NOT NULL,
    nbDominos int DEFAULT 0
);

CREATE TABLE Tour (
    idTour serial PRIMARY KEY,
    idPartie int REFERENCES Partie NOT NULL,
    tourRestant int DEFAULT 0
);

CREATE TABLE StatJeu (
    idStatJeu serial PRIMARY KEY,
    nbPartiesJouees int DEFAULT 0,
    tempsJoue int DEFAULT 0, /*en secondes*/
    dernierLancement timestamp NOT NULL,
    nbJoueursCrees int DEFAULT 0
);

CREATE TABLE Coup (
    idCoup serial PRIMARY KEY,
    nbPartie int REFERENCES Partie NOT NULL,
    idJoueur int REFERENCES Joueur NOT NULL,
    date timestamp DEFAULT current_timestamp,
    action text NOT NULL
);

CREATE TABLE TerrainType (
    idTerrainType serial PRIMARY KEY,
    terrain text NOT NULL
);

CREATE TABLE Paysage (
    idPaysage serial PRIMARY KEY,
    nbCouronne int DEFAULT 0,
    idTerrainType int REFERENCES TerrainType NOT NULL
);

CREATE TABLE Domino (
    idDomino serial PRIMARY KEY,
    idPaysage1 int REFERENCES Paysage(idPaysage) NOT NULL,
    idPaysage2 int REFERENCES Paysage(idPaysage) NOT NULL
);

INSERT INTO TerrainType VALUES (DEFAULT,'CHAMP');
INSERT INTO TerrainType VALUES (DEFAULT,'FORET');
INSERT INTO TerrainType VALUES (DEFAULT,'LAC');
INSERT INTO TerrainType VALUES (DEFAULT,'PRAIRIE');
INSERT INTO TerrainType VALUES (DEFAULT,'MARAIS');
INSERT INTO TerrainType VALUES (DEFAULT,'MINE');
INSERT INTO TerrainType VALUES (DEFAULT,'CHATEAU');
INSERT INTO TerrainType VALUES (DEFAULT,'VIDE');
INSERT INTO TerrainType VALUES (DEFAULT,'HORSJEU');

INSERT INTO Paysage VALUES (DEFAULT,0,1);
INSERT INTO Paysage VALUES (DEFAULT,1,1);
INSERT INTO Paysage VALUES (DEFAULT,0,2);
INSERT INTO Paysage VALUES (DEFAULT,1,2);
INSERT INTO Paysage VALUES (DEFAULT,0,3);
INSERT INTO Paysage VALUES (DEFAULT,1,3);
INSERT INTO Paysage VALUES (DEFAULT,0,4);
INSERT INTO Paysage VALUES (DEFAULT,1,4);
INSERT INTO Paysage VALUES (DEFAULT,2,4);
INSERT INTO Paysage VALUES (DEFAULT,0,5);
INSERT INTO Paysage VALUES (DEFAULT,1,5);
INSERT INTO Paysage VALUES (DEFAULT,2,5);
INSERT INTO Paysage VALUES (DEFAULT,0,6);
INSERT INTO Paysage VALUES (DEFAULT,1,6);
INSERT INTO Paysage VALUES (DEFAULT,2,6);
INSERT INTO Paysage VALUES (DEFAULT,3,6);
INSERT INTO Paysage VALUES (DEFAULT,0,7);
INSERT INTO Paysage VALUES (DEFAULT,0,8);
INSERT INTO Paysage VALUES (DEFAULT,0,9);

INSERT INTO Domino VALUES (1,1,1);
INSERT INTO Domino VALUES (2,1,1);
INSERT INTO Domino VALUES (3,3,3);
INSERT INTO Domino VALUES (4,3,3);
INSERT INTO Domino VALUES (5,3,3);
INSERT INTO Domino VALUES (6,3,3);
INSERT INTO Domino VALUES (7,5,5);
INSERT INTO Domino VALUES (8,5,5);
INSERT INTO Domino VALUES (9,5,5);
INSERT INTO Domino VALUES (10,7,7);
INSERT INTO Domino VALUES (11,7,7);
INSERT INTO Domino VALUES (12,10,10);
INSERT INTO Domino VALUES (13,1,3);
INSERT INTO Domino VALUES (14,1,5);
INSERT INTO Domino VALUES (15,1,7);
INSERT INTO Domino VALUES (16,1,10);
INSERT INTO Domino VALUES (17,3,5);
INSERT INTO Domino VALUES (18,3,7);
INSERT INTO Domino VALUES (19,2,3);
INSERT INTO Domino VALUES (20,2,5);
INSERT INTO Domino VALUES (21,2,7);
INSERT INTO Domino VALUES (22,2,10);
INSERT INTO Domino VALUES (23,2,13);
INSERT INTO Domino VALUES (24,4,1);
INSERT INTO Domino VALUES (25,4,1);
INSERT INTO Domino VALUES (26,4,1);
INSERT INTO Domino VALUES (27,4,1);
INSERT INTO Domino VALUES (28,4,5);
INSERT INTO Domino VALUES (29,4,7);
INSERT INTO Domino VALUES (30,6,1);
INSERT INTO Domino VALUES (31,6,1);
INSERT INTO Domino VALUES (32,6,3);
INSERT INTO Domino VALUES (33,6,3);
INSERT INTO Domino VALUES (34,6,3);
INSERT INTO Domino VALUES (35,6,3);
INSERT INTO Domino VALUES (36,1,8);
INSERT INTO Domino VALUES (37,5,8);
INSERT INTO Domino VALUES (38,1,11);
INSERT INTO Domino VALUES (39,7,11);
INSERT INTO Domino VALUES (40,14,1);
INSERT INTO Domino VALUES (41,1,9);
INSERT INTO Domino VALUES (42,5,9);
INSERT INTO Domino VALUES (43,1,12);
INSERT INTO Domino VALUES (44,7,12);
INSERT INTO Domino VALUES (45,15,1);
INSERT INTO Domino VALUES (46,10,15);
INSERT INTO Domino VALUES (47,10,15);
INSERT INTO Domino VALUES (48,1,16);

INSERT INTO Joueur VALUES (DEFAULT,'rouge',DEFAULT,DEFAULT);
INSERT INTO Joueur VALUES (DEFAULT,'bleu',DEFAULT,DEFAULT);
INSERT INTO Joueur VALUES (DEFAULT,'jaune',DEFAULT,DEFAULT);
INSERT INTO Joueur VALUES (DEFAULT,'vert',DEFAULT,DEFAULT);

SELECT * FROM Partie;