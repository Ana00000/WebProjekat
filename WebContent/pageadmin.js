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
	let status = $('<td>'+apartment.status+'</td>');
	let location = $('<td>' + apartment.location.address.street + ', ' +apartment.location.address.place + ', ' +apartment.location.address.country + '</td>');
	let forLogIn = $('<td>'+apartment.forLogIn+'</td>');
	let forLogOff = $('<td>'+apartment.forLogOff+'</td>');
	let allAmenities = [];
	allAmenities = $('<td></td>');
	
	
	$.each(apartment.amenities, function(i,amenity){
		if(!amenity.alive.localeCompare("true"))
			allAmenities.append(amenity.name);
		
		allAmenities.append(" ");
   	});
	let btnSelect =$('<td><button id="btnSelectChange">Edit</button>   <button id="btnSelectDelete">Delete</button></td>');

	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(status).append(location).append(forLogIn).append(forLogOff).append(allAmenities).append(btnSelect);
	$('#ApartmentsTable tbody').append(tr);
}


function addRes(reservation){
	let tr = $('<tr></tr>');
	let id = $('<td>'+reservation.id+'</td>');
	let rented = $('<td>'+reservation.rented+'</td>');
	let startReservation = $('<td>'+reservation.startReservation+'</td>');
	let overnightStay = $('<td>'+reservation.overnightStay+'</td>');
	let fullPrice = $('<td>'+reservation.fullPrice+'</td>');
	let welcomeMessage = $('<td>'+reservation.welcomeMessage+'</td>');
	let guest = $('<td>'+reservation.guest.username+'</td>');
	let status = $('<td>'+reservation.status+'</td>');

	tr.append(id).append(rented).append(startReservation).append(overnightStay).append(fullPrice).append(welcomeMessage).append(status).append(guest);
	$('#ReservationsTable tbody').append(tr);
}


function addComment(com, id){
	let c = $('<li> Apartment with id ' + id + ' has comment: ' + com + '</li>');
	$('#listCommentsAdmin').append(c);
}

var allReservation = new Array();

var allApartments = new Array();

var comments = new Array();

var allComments = new Array();

var allQuestions = new Array();

var oneComment;

function getComments(id) {
	oneComment = "no comment";
	$.each(comments, function(i,comment){
	   	if(!comment.id.localeCompare(id))
	   		oneComment = comment.text;
   	});
}

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
	    
	    $.getJSON("comments.json", function (data) {
	    	comments = data;
		});
	    
	    $.getJSON("apartments.json", function (data) {
	    	allApartments = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allApartments, function(i,apartment){
		        	if(!apartment.alive.localeCompare("true"))
		        		addApp(apartment);
		        	});
		        
		    });
	    
	    $.getJSON("reservations.json", function (data) {
	    	allReservation = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allReservation, function(i,reservation){
		        		if(!reservation.alive.localeCompare("true"))
		        			addRes(reservation);
		        	});
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allComments  = data;
		    })
		    .done(function() {
		        console.log( "Another JSON loaded!" );
		        $.each(allComments, function(i,apartment){
			        	 $.each(apartment.comments, function(i, comment){
			        		 	getComments(comment.id);
			        			if (oneComment.localeCompare("no comment") && !apartment.alive.localeCompare("true")) 
			        				addComment(oneComment, apartment.id);
					        });
		        	});
		    });
	    
	    $("#UsersTable").on('click','.btnSelect',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
	    });
	    
	    
	    $("#ApartmentsTable").on('click','#btnSelectDelete',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         
	         $.ajax({
	 			url: 'rest/apartments/deleteApartment',
	 			data: JSON.stringify({id: id}),
	 			contentType: 'application/json',
	 			type:'DELETE',
	 			success: function() {
	 				alert("Apartment successfully removed!");
	 				window.location.href= 'pageadmin.html';
	 			},
	 			error: function(message){
	 				let name = message.responseText;
	 				$('p#error').text(name);
	 				$('p#error').css('color','red');
	 			}
	 		});
	    });
	    
	    
	    $("#ApartmentsTable").on('click','#btnSelectChange',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         
	       $.ajax({
 		    url: 'rest/apartments/selectedApartment',
 		    data: JSON.stringify({id: id}),
			contentType: 'application/json',
			type:'PUT',
 		    success: function() {
 		    	console.log("App set");
 		    	window.location.href= 'apartmentChange.html';
 		    },
         	error: function(){
         		console.log("App not set");
         	} 		
	 		});

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


function sortTableReservations(n) {
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

function sortTableNumberReservations(n) {
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

function myFunctionType() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputType");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
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

function myFunctionStatus() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputStatus");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
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




function myFunctionAmenities() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputAmenities");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
	  tr = table.getElementsByTagName("tr");

	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[8];
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

function myFunctionDateIn() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputDateIn");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
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


function myFunctionDateOff() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputDateOff");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
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
