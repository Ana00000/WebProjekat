function addAcc(account){
	let tr = $('<tr></tr>');
	let username = $('<td>'+account.username+'</td>');
	let name = $('<td>'+account.name+'</td>');
	let surname = $('<td>'+account.surname+'</td>');
	let role = $('<td>'+account.role+'</td>');
	let gender = $('<td>'+account.gender+'</td>');
	let password = $('<td>'+account.password+'</td>');
	let btnSelect =$('<td><button class="btnSelect">Edit</button>   <button class="btnSelect">Delete</button></td>');

	tr.append(username).append(name).append(surname).append(role).append(gender).append(password).append(btnSelect);
	$('#UsersTable tbody').append(tr);
}

function addApp(apartment){
	let tr = $('<tr></tr>');
	let id = $('<td>'+apartment.id+'</td>');
	let type = $('<td>'+apartment.type+'</td>');
	let nbrRooms = $('<td>'+apartment.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+apartment.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+apartment.pricePerNight+'</td>');
	let btnSelect =$('<td><button class="btnSelectApp">Edit</button>   <button class="btnSelectApp">Delete</button></td>');

	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(btnSelect);
	$('#ApartmentsTable tbody').append(tr);
}


function addRes(reservation){
	let tr = $('<tr></tr>');
	let id = $('<td>'+reservation.id+'</td>');
	let rented = $('<td>'+reservation.rented.id+'</td>');
	let startReservation = $('<td>'+reservation.startReservation+'</td>');
	let overnightStay = $('<td>'+reservation.overnightStay+'</td>');
	let fullPrice = $('<td>'+reservation.fullPrice+'</td>');
	let welcomeMessage = $('<td>'+reservation.welcomeMessage+'</td>');
	let status = $('<td>'+reservation.status+'</td>');

	tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status);
	$('#ReservationsTable tbody').append(tr);
}


var allReservation = new Array();

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
	    
	    $.getJSON("reservations.json", function (data) {
	    	allReservation = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allReservation, function(i,reservation){
			    	   addRes(reservation);
		        	});
		    });
	    
	    $("#UsersTable").on('click','.btnSelect',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
	    });
	    
	    $("#ApartmentsTable").on('click','.btnSelectApp',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
	    });
});

function sortTableUsers(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("UsersTable");
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


	function sortTable(n) {
		  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		  table = document.getElementById("ApartmentsTable");
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
			  table = document.getElementById("ApartmentsTable");
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
	

	function myFunctionUsername() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputUsername");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("UsersTable");
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


	function myFunctionRole() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputRole");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("UsersTable");
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


	
	function myFunctionGender() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputGender");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("UsersTable");
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
	
	



