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
	let btnSelect =$('<td><button class="btnSelect">Edit</button>   <button class="btnSelect">Delete</button></td>');	
	
	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(btnSelect);
	$('#ApartmentsInactiveTable tbody').append(tr);
}

function addApActive(active){
	let tr = $('<tr></tr>');
	let id = $('<td>'+active.id+'</td>');
	let type = $('<td>'+active.type+'</td>');
	let nbrRooms = $('<td>'+active.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+active.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+active.pricePerNight+'</td>');
	let btnSelect =$('<td><button class="btnSelect">Edit</button>   <button class="btnSelect">Delete</button></td>');	
	
	tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(btnSelect);
	$('#ApartmentsActiveTable tbody').append(tr);
}

var allQuestions = new Array();
var allInactive = new Array();
var allActive = new Array();
var host;

$(document).ready(function() {
			
		$.get({
		    url: 'rest/loggedUser',
		    success: function(user) {
		    	host = user;
		    }
		});
	
	    $.getJSON("reservations.json", function (data) {
	        allQuestions = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,account){
		        	if(!account.rented.host.username.localeCompare(host.username))
		        		addAcc(account);
		        	});
		        
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allInactive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allInactive, function(i,inactive){
		        	if(!inactive.status.localeCompare("INACTIVE"))
		        		addApInactive(inactive);
		        	});
		        
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allActive = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allActive, function(i,active){
		        	if(!active.status.localeCompare("ACTIVE"))
		        		addApActive(active);
		        	});
		        
		    });
	    
	    $("#ApartmentsInactiveTable").on('click','.btnSelect',function(){
	         var currentRow=$(this).closest("tr"); 
	         
	         var col1=currentRow.find("td:eq(0)").text();
	         
	         alert(col1);
	    });
	    
	    $("#ApartmentsActiveTable").on('click','.btnSelect',function(){
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



