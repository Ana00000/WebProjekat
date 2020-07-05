var host;

$(document).ready(function() {
	
	$.get({
	    url: 'rest/loggedUser',
	    success: function(user) {
	    	host = user;
	    }
	});
	
	$('button#addAp').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let type=$("select#type option:checked" ).val();
		let nbrRooms=$('input[name=nbrRooms]').val();
		let nbrGuests=$('input[name=nbrGuests]').val();
		let pricePerNight=$('input[name=pricePerNight]').val();
		let status="INACTIVE";
		
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[6]).remove();
		}
		
		if (id == null || id === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Id shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (nbrRooms == null || nbrRooms === ""|| nbrRooms<0) {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Number of rooms is incorrect or empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (nbrGuests == null || nbrGuests === ""|| nbrGuests < 0) {
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Number of guests is incorrect or empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (pricePerNight == null || pricePerNight === ""|| pricePerNight < 0) {
			let tr = $($('table tbody tr')[4]);
			let td = $('<td>Price per night is incorrect or empty. </td>');
			tr.append(td);
			flag = false;
		}

		
		if(flag){
			$.ajax({
				url: 'rest/apartments/addApartment',
				data: JSON.stringify({id: id, type: type, nbrRooms: nbrRooms, nbrGuests: nbrGuests, host: host, pricePerNight: pricePerNight, status: status}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					alert("Apartment successfully added!");
					window.location.href= 'pagehost.html';
				},
				error: function(message){
					if(message.status==400)
						alert("Unsuccessful adding, id is not unique!");
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
			});
		}
	});
});