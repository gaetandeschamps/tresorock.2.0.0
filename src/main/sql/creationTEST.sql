/*Constructeur Table ClientDao*/
CREATE TABLE ClientTEST (
    IdClient int(11) NOT NULL AUTO_INCREMENT,
    Nom char(20) NOT NULL,
    Prenom char(20) NOT NULL,
    Ecole char(20) NOT NULL,
    Cotisant Boolean,
    Statut Char(20) NOT NULL,
    PRIMARY KEY (IdClient));

/*Constructeur Table Participe*/
CREATE TABLE ParticipeTEST (
    IdSoiree int(11) NOT NULL,
    IdClient int(11) NOT NULL,
    PrixPaye double NOT NULL,
    PRIMARY KEY (IdSoiree, IdClient)
    );
    /*CONSTRAINT fk_idClient
        FOREIGN KEY (IdClient)             -- Colonne sur laquelle on crée la clé
        REFERENCES Client(IdClient),
    CONSTRAINT fk_idSoiree
        FOREIGN KEY (idSoiree)
        REFERENCES Soiree(idSoiree));*/

/*Constructeur Table Soiree*/
CREATE TABLE SoireeTEST (
    IdSoiree int(11) NOT NULL AUTO_INCREMENT,
    DateSoiree date NOT NULL,
    RecetteDeCaisse double NOT NULL,
    ErreurDeCaisse double NOT NULL,
    Theme char(40) NOT NULL,
    Actif Boolean,
    PRIMARY KEY (idSoiree));






