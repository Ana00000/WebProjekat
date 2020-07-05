var guestCurrent;
var selectedApartment;

$(document).ready(function() {
	
	$.get({
	    url: 'rest/loggedUser',
	    success: function(user) {
	    	guestCurrent = user;
	    }
	});
	
     $.get({
            url: 'rest/apartments/currentApartment',
            success: function(apartment) {
                console.log(apartment);
                selectedApartment=apartment.id;
            }
        });
	
	$('button#adding').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let rented=selectedApartment;
		let startReservation=$('input[name=startReservation]').val();
		let overnightStay=$('input[name=overnightStay]').val();
		let welcomeMessage=$('input[name=welcomeMessage]').val();
		let guest = "Ana123";
		let status="CREATED";
		welcomeMessage = "safsa";
		overnightStay = 3;
		startReservation = "11/11/2020";
		rented = "1098";
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[4]).remove();
		}
		
		if (id == null || id === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Id shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (startReservation == null || startReservation === "") {
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Start of reservation shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (overnightStay == null || overnightStay === "") {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Overnight stay shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(overnightStay<0){
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>P>Overnight stay shouldn\'t be negative number. </td>');
			tr.append(td);
			flag = false;
		}

		if (welcomeMessage == null || welcomeMessage === "") {
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Welcome message shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(welcomeMessage.length > 30){
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Welcome message is too long ,it should contain maximum 30 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if(flag){
			$.ajax({
				url: 'rest/reservations/addReservation',
				data: JSON.stringify({id: id, rented: rented, startReservation: startReservation, overnightStay: overnightStay, welcomeMessage: welcomeMessage,guest: guest, status: status}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					window.location.href= 'apartments.html';
				},
				error: function(message){
					if(message.status==400)
						alert("Reservation already exists!");
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
				
			});
		}
	});
});