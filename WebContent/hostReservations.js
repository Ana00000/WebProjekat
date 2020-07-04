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
	let btnSelect =$('<td><button class="btnSelect">Cancel</button></td>');

	tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status).append(guest).append(btnSelect);
	$('#ReservationsTable tbody').append(tr);
}

var allReservation = new Array();
var allApartments = new Array();
var host;
var oneApartment;

function getApartments(id) {
	oneApartment = "";
	$.each( allApartments, function(i,apartment){
	   	if(apartment.id == id)
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
		        	if(!oneApartment.localeCompare(host.username))
		        		addRes(reservation);
		        	});
		    });
	    
	    $("#ReservationsTable").on('click','.btnSelect',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
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




