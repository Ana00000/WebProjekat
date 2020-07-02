function addAcc(account){
	let tr = $('<tr></tr>');
	let id = $('<td>'+account.id+'</td>');
	let type = $('<td>'+account.type+'</td>');
	let nbrRooms = $('<td>'+account.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+account.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+account.pricePerNight+'</td>');
	let tdTest = $('<td></td>');

	if(!account.status.localeCompare("ACTIVE"))
		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(tdTest);
	$('#ApartmentsTable tbody').append(tr);
}

var allQuestions = new Array();

$(document).ready(function() {
	    $.getJSON("apartments.json", function (data) {
	        allQuestions = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,user){
			        addAcc(user);
		        	});
		        
		    });
});

