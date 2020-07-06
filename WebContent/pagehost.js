function addAcc(account){
	let tr = $('<tr></tr>');
	let username = $('<td>'+account.guest.username+'</td>');
	let name = $('<td>'+account.guest.name+'</td>');
	let surname = $('<td>'+account.guest.surname+'</td>');
	let role = $('<td>'+account.guest.role+'</td>');
	let gender = $('<td>'+account.guest.gender+'</td>');
	let password = $('<td>'+account.guest.password+'</td>');
	
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
	let status = $('<td>'+inactive.status+'</td>');
	let btnSelect =$('<td><button class="btnSelectChange">Edit</button>   <button class="btnSelectDelete">Delete</button></td>');	
	
	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(status).append(btnSelect);
	$('#ApartmentsInactiveTable tbody').append(tr);
}

function addApActive(active){
	let tr = $('<tr></tr>');
	let id = $('<td>'+active.id+'</td>');
	let type = $('<td>'+active.type+'</td>');
	let nbrRooms = $('<td>'+active.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+active.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+active.pricePerNight+'</td>');
	let status = $('<td>'+active.status+'</td>');
	let btnSelect =$('<td><button class="btnSelectChange">Edit</button>   <button class="btnSelectDelete">Delete</button></td>');	
	
	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(status).append(btnSelect);
	$('#ApartmentsActiveTable tbody').append(tr);
}

var allQuestions = new Array();
var allInactive = new Array();
var allActive = new Array();
var host;
var oneApartment;

function getApartments(id) {
	oneApartment = "";
	$.each(allActive, function(i,apartment){
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
	    	allActive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allActive, function(i,active){
		        	if(!active.status.localeCompare("ACTIVE") && !active.alive.localeCompare("true"))
		        		addApActive(active);
		        	});
		        
		 });
	
	    $.getJSON("reservations.json", function (data) {
	        allQuestions = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,account){
		        	getApartments(account.rented);
		        	if(!oneApartment.localeCompare(host.username) && !account.alive.localeCompare("true"))
		        		addAcc(account);
		        	});
		        
		 });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allInactive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allInactive, function(i,inactive){
		        	if(!inactive.status.localeCompare("INACTIVE") && !inactive.alive.localeCompare("true"))
		        		addApInactive(inactive);
		        	});
		        
		    });
	    
	    $("#ApartmentsInactiveTable").on('click','.btnSelectDelete',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         
	         $.ajax({
	 			url: 'rest/apartments/deleteApartment',
	 			data: JSON.stringify({id: id}),
	 			contentType: 'application/json',
	 			type:'DELETE',
	 			success: function() {
	 				alert("Apartment successfully removed!");
	 				window.location.href= 'pagehost.html';
	 			},
	 			error: function(message){
	 				let name = message.responseText;
	 				$('p#error').text(name);
	 				$('p#error').css('color','red');
	 			}
	 		});
	    });
	    
	    $("#ApartmentsInactiveTable").on('click','.btnSelectChange',function(){
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
	    
	    $("#ApartmentsActiveTable").on('click','.btnSelectDelete',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var id=currentRow.find("td:eq(0)").text();
	         
	         $.ajax({
	 			url: 'rest/apartments/deleteApartment',
	 			data: JSON.stringify({id: id}),
	 			contentType: 'application/json',
	 			type:'DELETE',
	 			success: function() {
	 				alert("Apartment successfully removed!");
	 				window.location.href= 'pagehost.html';
	 			},
	 			error: function(message){
	 				let name = message.responseText;
	 				$('p#error').text(name);
	 				$('p#error').css('color','red');
	 			}
	 		});
	    });
	    
	    $("#ApartmentsActiveTable").on('click','.btnSelectChange',function(){
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
	  table = document.getElementById("ApartmentsActiveTable");
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
	  table = document.getElementById("ApartmentsActiveTable");
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

function myFunctionTypeIn() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputTypeIn");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsInactiveTable");
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

function myFunctionType() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputType");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsActiveTable");
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

function myFunctionStatusIn() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputStatusIn");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsInactiveTable");
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
	  table = document.getElementById("ApartmentsActiveTable");
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



