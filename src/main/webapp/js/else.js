function startTime() 
{
    var today = new Date();     // récupération de l'heure client
    var h = today.getHours();   // extraction de l'heure
    var m = today.getMinutes(); // extraction des minutes
    var s = today.getSeconds(); // extraction des secondes
    var day = today.getDate();
    var month = today.getMonth()+1  ;
    var year = today.getFullYear();

    h = checkTime(h);           // appel de la fonction ci-dessous
    m = checkTime(m);           // pour
    s = checkTime(s);           // formattage de l'affichage : ajout d'un zéro
    day = checkTime(day);
    month = checkTime(month);
    year = checkTime(year);

    document.getElementById('date').innerHTML =  "<h3>"+ day + "/" + month + "/" + year+"</h3>";
    document.getElementById('txt').innerHTML =  "<h3>"+h + ":" + m + ":" + s + " " +"</h3>";    
    var t = setTimeout(startTime, 1000);  // rappel de la fonction toutes les secondes
    
};

function checkTime(i) 
{
    if (i < 10) 
    {
        i = "0" + i ;   // ajout d'un zéro devant les nombres < 10
    }
    return i;
};

function TypeFormulaireTEA(button)
{
 var prix;
 var titre;
 if(button.value=="membre")
 {
    titre = document.getElementById("titreForm");
    titre.innerHTML = "<i>MODE TEA - Formulaire Abonné Membre</i>";
    prix = document.getElementById("montant2");
    prix.value="0.00";
 }
 if(button.value=="inscription")
 {
    titre = document.getElementById("titreForm");
    titre.innerHTML = "<i>MODE TEA - Formulaire d'Inscription Abonné</i>";
    prix = document.getElementById("montant2");
    prix.value="20.00";
 }
 if (button.value=="ancienMembre") 
 {
    titre = document.getElementById("titreForm");
    titre.innerHTML = "<i>MODE TEA - Formulaire Ancien Membre</i>";
    prix = document.getElementById("montant2");
    prix.value="15.00";
 }
 var typeFormulaire=document.getElementById('formulaireClient');
 typeFormulaire.innerHTML=message;
};

function CreerEvenement(){
        document.getElementById("suite").style.display = "block";
        document.getElementById("suite2").style.display = "block";
        document.getElementById("suite3").style.display = "block";
        document.getElementById("suite4").style.display = "block";
        document.getElementById("suite5").style.display = "block";
        document.getElementById("suite6").style.display = "block";
        document.getElementById("suite7").style.display = "block";

};
function ClickSoiree(){
    document.getElementById("suite11").style.display = "block";
    document.getElementById("suite22").style.display = "block";
    document.getElementById("suite33").style.display = "block";
    document.getElementById("suite44").style.display = "block";
    document.getElementById("suite55").style.display = "block";
    document.getElementById("suite66").style.display = "block";
    document.getElementById("suite77").style.display = "block";

};

function OnClick(){
    alert("Merci, votre évènement est créé.");
};

function NouveauClientNormal(input){
    if (input.value=="HEI") {
        document.getElementById("cotisant").style.display = "inline";
    } else if(input.value!="HEI"){
        document.getElementById("cotisant").style.display= "none";
    }

};

function NouveauClientNormal2(input){
    if (input.value=="HEI") {
        document.getElementById("cotisant2").style.display = "inline";
    } else if(input.value!="HEI"){
        document.getElementById("cotisant2").style.display= "none";
    }

};

function PrixAPayer(){
    var txt;
    if(document.getElementById("Couple").checked){
        txt = document.getElementById("montant");
        txt.value="1.50";
        }else if(document.getElementById("seul").checked){
        txt=document.getElementById("montant");
        txt.value="2.00";
        }
    txt.setAttribute("name", "prixPaye");
};

/**
 * Cette méthode permet d'enregistrer dans la BDD les données entrées par le TEA après complétion du formulaire et clic sur le bouton de validation
 * @constructor
 */
function Valider(){
    alert("Merci ! Votre entrée a été enregistrée.");
};

/**
 * Cette méthode permet d'ajuster la valeur du statut du client en fonction du formulaire utilisé
 */
function changementName(input){
    if(input=='normal'){
        document.getElementById("statutNormal").setAttribute("statut", "normal");
    }else{
        document.getElementById("statutNormal").setAttribute("statut", "abonne");
    }
}

/**
 * Cette méthode permet d'ajuster la valeur de l'erreur de caisse en fonction de la recette du Fichier et de la recette de la caisse
 */
function updateErreurCaisse(elem){
    //console.log(elem.value);
    if(elem.value==null){
        document.getElementById("eFichier").setAttribute("value", 0);
    }else{
        var rCaisse = elem.value;
        var rFichier = document.getElementById("rFichier").getAttribute("value");
        console.log(rCaisse+" - "+rFichi);
        document.getElementById("eFichier").setAttribute("value", eCaisse);
    }
}

/**
 * Cette méthode permet de modifier le bouton de création d'une soirée en fonction de l'existance d'une soirée en cours
 */
function updateBoutonValidation(){
    alert("Clôture de la soirée !");
}

/**
 * Cette requeteAJAX en GET permet d'afficher, si c'est le cas, la soirée qui est en cours
 */
function affichageSoireeEnCours() {
    var affichageSoireeEnCoursRequest = new XMLHttpRequest();
    affichageSoireeEnCoursRequest.open("GET", "BilanSoirees", true);
    affichageSoireeEnCoursRequest.responseType="json";

    affichageSoireeEnCoursRequest.onload = function(){
        console.log(this.response);
        if (this.response.idSoiree!=-1) { //si on récupère une soirée active
            var sec = this.response;
            var date;
            var mois;
            var jour;

            //ajustmement du mois
            if (0 < sec.dateSoiree.month && sec.dateSoiree.month < 10) {
                mois = "0" + sec.dateSoiree.month;
            } else {
                mois = sec.dateSoiree.month;
            }
            //ajustement du jour
            if (0 < sec.dateSoiree.day && sec.dateSoiree.day < 10) {
                jour = "0" + sec.dateSoiree.day;
            } else {
                jour = sec.dateSoiree.day;
            }

            //concaténation
            date = sec.dateSoiree.year + "-" + mois + "-" + jour;
            console.log("Affectation des \"0\" : " + date);

            //affectation au l'id du formulaire
            document.getElementById("example-date-input").setAttribute("value", date);

            CreerEvenement();

            //affection de la recette
            document.getElementById("rFichier").setAttribute("value", sec.recette);

            //affectation de l'erreur de caisse
            document.getElementById("eFichier").setAttribute("value", sec.erreurCaisse);

            //affectation du nb de pers venues
            document.getElementById("nbPers").setAttribute("value", sec.nbClients);

            //affectation du nb d'abos venus
            document.getElementById("nbAbos").setAttribute("value", sec.nbClientsAbos);

            //affectation du theme
            document.getElementById("themeSoiree").setAttribute("value", sec.theme);

            //changementdeDoPOST
            document.getElementsByName("action")[0].setAttribute("value", "modifierAct");

            //bouton valider affiche "Finaliser" et remet actif=0
            document.getElementsByName("action")[0].setAttribute("value", "changerAct");
            var nouveaubouton = document.getElementById("suite7").firstElementChild.cloneNode(false);
            console.log(nouveaubouton);
            document.getElementById("suite7").appendChild(nouveaubouton);
            var liste = document.getElementById("suite7").getElementsByTagName('button');
            document.getElementById("suite7").removeChild(liste[0]);
            document.getElementById("suite7").firstElementChild.setAttribute("id", "newbouton");
            document.getElementById("suite7").firstElementChild.innerHTML="Finaliser";
            document.getElementById("suite7").firstElementChild.setAttribute("onclick", "updateBoutonValidation()");

        }
    }

    affichageSoireeEnCoursRequest.send();

}
affichageSoireeEnCours();

function erreurTEA() {

    var urlString = location.href;
    var urlEnd="";
    urlEnd = urlString.substr(urlString.length-4, urlString.length);
    console.log(urlString);
    console.log(urlEnd);
    if(urlEnd=="?e=1"){
        alert("Veuillez d'abord créer une soirée dans le mode Admin !");
    }

}

erreurTEA();