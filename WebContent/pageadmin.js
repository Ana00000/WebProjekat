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

function addApp(apartment){
	let tr = $('<tr></tr>');
	let id = $('<td>'+apartment.id+'</td>');
	let type = $('<td>'+apartment.type+'</td>');
	let nbrRooms = $('<td>'+apartment.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+apartment.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+apartment.pricePerNight+'</td>');

	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight);
	$('#ApartmentsTable tbody').append(tr);
}

var allApartments = new Array();

var allQuestions = new Array();

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
	    	allApartments = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allApartments, function(i,apartment){
		        	addApp(apartment);
		        	});
		        
		    });
});




