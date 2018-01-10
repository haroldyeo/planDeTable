// viewModel object
var vm = {
	agents : ko.observable(),
	master : ko.observable(),
	agentSelected : ko.observable(),
	comptes : ko.observableArray(),
	displayModal : function(data) {
		onSubmitDetails(data);
	}
};

// MAJ des données d'agents
function doMajAgent(data) {
	vm.agents(data);
}

// MAJ détails de l'agent
function doMajAgentSelected(data){
	vm.agentSelected(data);
//	alert(vm.agentSelected().nom +"    -    " +vm.agentSelected().prenoms);
}

//MAJ des comptes
function doMAJComptes(data) {
	vm.comptes(data);
}

// MAJ des infos des comptes

// Recherche de 1er niveau
function onSubmit(nom, prenoms, dateNaissance, piece, compteContri, numero, idCompte) {
	var data = {
		nom : nom,
		prenoms : prenoms,
		dateNaissance : dateNaissance,
		piece : piece,
		compteContri : compteContri,
		numero : numero,
		idCompte: idCompte
	};

	$.post("home", data, function(response) {
		
		if(response.length > 0 && response != '[]'){
			var r = JSON.parse(response);
			if(r["limit"]==true){
				$("#divNoData").text("La période d'essai de cette application a expiré, veuillez contacter l'administrateur").slideDown("3000").delay(4000).slideUp("3000");
			} else{
				var comptesAndMaster = JSON.parse(response); 
////			var comptes = comptesAndMaster[0];
				doMajAgent(comptesAndMaster);
	//			var masterId = comptesAndMaster[1];
	//			vm.master(masterId);
			}			
		}else{
			doMajAgent('');
			$("#divNoData").text("Aucune donnée ne correspond aux critères de recherche").slideDown("3000").delay(4000).slideUp("3000");
		}
			
	}).fail(function() {
		alert('Une Erreur est survenue!');
	});

}


// Recherche par id pour faire afficher le popup des comptes
function onSubmitDetails(obj) {
	var data = {
		compteId : obj.id,
		phone: obj.numero
	};
	$.post("detailsCompte", data, function(response) {
		var detailCompte = JSON.parse(response);
		var compte = detailCompte[0]; 
		doMAJComptes(compte);
		
	}).fail(function() {
		alert('Une Erreur est survenue!');
	});

	
}


// Activates knockout.js
ko.applyBindings(vm);
