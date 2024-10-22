var app;
$(document).ready(function() {
	 $.get({
	        url: 'rest/apartments/currentApartment',
	        success: function(apartment) {
	        	
	        	console.log(apartment);
	        	app=apartment;
	        	$("#id").val(apartment.id);
	        	$("#type").val(apartment.type.toString());
	        	$("#nbrRooms").val(apartment.nbrRooms);
	        	$("#nbrGuests").val(apartment.nbrGuests);
	        	$("#pricePerNight").val(apartment.pricePerNight);
	        	$("#status").val(apartment.status.toString());
	        	$("#street").val(apartment.location.address.street);
	        	$("#place").val(apartment.location.address.place);
	        	$("#country").val(apartment.location.address.country);
		        
	        }
	    });
	
	$('button#changeAp').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let type=$("select#type option:checked" ).val();
		let nbrRooms=$('input[name=nbrRooms]').val();
		let nbrGuests=$('input[name=nbrGuests]').val();
		let pricePerNight=$('input[name=pricePerNight]').val();
		let street=$('input[name=street]').val();
		let place=$('input[name=place]').val();
		let country=$('input[name=country]').val();
		let status=$("select#status option:checked" ).val();
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[9]).remove();
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
				url: 'rest/apartments/setApartment',
				data: JSON.stringify({id: id, type: type, nbrRooms: nbrRooms, nbrGuests: nbrGuests, pricePerNight: pricePerNight , status: status}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					alert("Apartment successfully changed!");
					window.location.href= 'apartmentChange.html';
				},
				error: function(message){
					if(message.status==400)
						alert("Id shouldn't be changed!");
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
			});
		}
	});
});