function addRes(reservation){
	let tr = $('<tr></tr>');
	let id = $('<td>'+reservation.id+'</td>');
	let rented = $('<td>'+reservation.rented+'</td>');
	let startReservation = $('<td>'+reservation.startReservation+'</td>');
	let overnightStay = $('<td>'+reservation.overnightStay+'</td>');
	let fullPrice = $('<td>'+reservation.fullPrice+'</td>');
	let welcomeMessage = $('<td>'+reservation.welcomeMessage+'</td>');
	let status = $('<td>'+reservation.status+'</td>');
	let guest = $('<td>'+reservation.guest.username+'</td>');
	
	if(!reservation.status.localeCompare("CREATED"))
	{
		let btnSelect =$('<td><button class="btnAccept">Accept</button><button class="btnCancel">Cancel</button></td>');
		tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status).append(guest).append(btnSelect);
		$('#ReservationsTable tbody').append(tr);
	}
	
	else if(!reservation.status.localeCompare("ACCEPTED"))
		{
			let btnSelect =$('<td><button class="btnCancel">Cancel</button></td>');
			tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status).append(guest).append(btnSelect);
			$('#ReservationsTable tbody').append(tr);
		}
	else{
		tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status).append(guest);
		$('#ReservationsTable tbody').append(tr);
	}

}

var allReservation = new Array();
var allApartments = new Array();
var host;
var oneApartment;

function getApartments(id) {
	oneApartment = "";
	$.each( allApartments, function(i,apartment){
	   	if(!apartment.id.localeCompare(id))
	   		oneApartment = apartment.host.username;
   	});
}

$(document).ready(function() {
		
	$.get({
		    url: 'rest/loggedUser',
		    success: function(user) {
		    	host = user;
		    }
		});
	
	    $.getJSON("apartments.json", function (data) {
	    	allApartments = data;
		});
	
	    $.getJSON("reservations.json", function (data) {
	    	allReservation = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allReservation, function(i,reservation){
		        	getApartments(reservation.rented);
		        	if(!oneApartment.localeCompare(host.username) && !reservation.alive.localeCompare("true"))
		        		addRes(reservation);
		        	});
		    });
	    
	    $("#ReservationsTable").on('click','.btnSelect',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
	    });
	    
	    
	    
	    $("#ReservationsTable").on('click','.btnCancel',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         var status=currentRow.find("td:eq(6)").text();
	         if(!status.localeCompare("CREATED") || !status.localeCompare("ACCEPTED"))
	        	 {
	     		
	        	 	status="CANCELLED";
		     			$.ajax({
		     				url: 'rest/reservations/setReservationGuest',
		     				data: JSON.stringify({id: id,status:status}),
		     				contentType: 'application/json',
		     				type:'PUT',
		     				success: function() {
		     					alert("Change was successful!");
		     					window.location.href= 'hostReservations.html';
		     				},
		     				error: function(message){
		     					if(message.status==400)
		     						alert("Change was unsuccessful!");
		     					let name = message.responseText;
		     					$('p#error').text(name);
		     					$('p#error').css('color','red');
		     				}
		     			});
	     		
	        	 }
	    });
	    
	    $("#ReservationsTable").on('click','.btnAccept',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         var status=currentRow.find("td:eq(6)").text();
	         if(!status.localeCompare("CREATED"))
	        	 {
	     		
	        	 status="ACCEPTED";
	     			$.ajax({
	     				url: 'rest/reservations/setReservationGuest',
	     				data: JSON.stringify({id: id,status:status}),
	     				contentType: 'application/json',
	     				type:'PUT',
	     				success: function() {
	     					alert("Change was successful!");
	     					window.location.href= 'hostReservations.html';
	     				},
	     				error: function(message){
	     					if(message.status==400)
	     						alert("Change was unsuccessful!");
	     					let name = message.responseText;
	     					$('p#error').text(name);
	     					$('p#error').css('color','red');
	     				}
	     			});
	     		
	        	 }
	    });
	    
	    
});

function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("ReservationsTable");
  switching = true;
  dir = "asc"; 
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
    	rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;      
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

function sortTableNumber(n) {
 var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("ReservationsTable");
  switching = true;
  dir = "asc"; 
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
    	  if (Number(x.innerHTML) > Number(y.innerHTML)) {
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
    	  if (Number(x.innerHTML) < Number(y.innerHTML)) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
    	rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;      
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
	


function myFunctionId() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputId");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}



function myFunctionApartment() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputApartment");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionStartDate() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputStartDate");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionOvernightStay() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputOvernightStay");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[3];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionFullPrice() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputFullPrice");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionWelcomeMessage() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputWelcomeMessage");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[5];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionStatus() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputStatus");
  filter = input.value.toUpperCase();
  table = document.getElementById("ReservationsTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[6];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function myFunctionGuest() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputGuest");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ReservationsTable");
	  tr = table.getElementsByTagName("tr");

	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[7];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
	}




