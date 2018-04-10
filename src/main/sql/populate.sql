INSERT INTO Client (idClient, Nom, Prenom, Ecole, Cotisant, Statut) VALUES
(1,'Sidonie', 'Frédéric', 'HEI', False, 'Normal'),
(2,'Juliette', 'Ginette', 'ISA', False, 'Normal'),
(3,'Léonide', 'Edith', 'HEI', True, 'Abonné'),
(4,'Eliane', 'Bastien', 'ISEN', False, 'Abonné');

INSERT INTO Participe (IdSoiree, IdClient, PrixPaye) VALUES
(1,1,2.0),
(1,2,1.5),
(1,3,20.0),
(1,4,20.0),

(2,1,2.0),
(2,3,0),
(2,2,2.0),

(3,1,2.0),

(4,1,2.0),
(4,4,0.0);

INSERT INTO Soiree (idSoiree,DateSoiree, RecetteDeCaisse, ErreurDeCaisse, Theme, Actif) VALUES
(1,'2018/01/10', 23.5, 0, '', False),
(2,'2018/01/17', 4.0, -0.5, '', False),
(3,'2018/01/24', 2.0, 0.25, 'Soiree Deguisement', False),
(4,'2018/01/31', 2.0, 0, '', False);






