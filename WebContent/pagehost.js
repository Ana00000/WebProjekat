function addAcc(account){
	let tr = $('<tr></tr>');
	let username = $('<td>'+account.username+'</td>');
	let name = $('<td>'+account.name+'</td>');
	let surname = $('<td>'+account.surname+'</td>');
	let role = $('<td>'+account.role+'</td>');
	let gender = $('<td>'+account.gender+'</td>');
	let password = $('<td>'+account.password+'</td>');

	tr.append(username).append(name).append(surname).append(role).append(gender).append(password);
	$('#UsersTable tbody').append(tr);
}

function addApInactive(inactive){
	let tr = $('<tr></tr>');
	let id = $('<td>'+inactive.id+'</td>');
	let type = $('<td>'+inactive.type+'</td>');
	let nbrRooms = $('<td>'+inactive.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+inactive.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+inactive.pricePerNight+'</td>');

	if(!inactive.status.localeCompare("INACTIVE"))
		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight);
	$('#ApartmentsInactiveTable tbody').append(tr);
}

function addApActive(active){
	let tr = $('<tr></tr>');
	let id = $('<td>'+active.id+'</td>');
	let type = $('<td>'+active.type+'</td>');
	let nbrRooms = $('<td>'+active.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+active.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+active.pricePerNight+'</td>');

	if(!active.status.localeCompare("ACTIVE"))
		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight);
	$('#ApartmentsActiveTable tbody').append(tr);
}

var allQuestions = new Array();
var allInactive = new Array();
var allActive = new Array();

$(document).ready(function() {
	    $.getJSON("users.json", function (data) {
	        allQuestions = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,user){
			        addAcc(user);
		        	});
		        
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allInactive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allInactive, function(i,inactive){
		        	addApInactive(inactive);
		        	});
		        
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allActive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allActive, function(i,active){
		        	addApActive(active);
		        	});
		        
		    });
});





