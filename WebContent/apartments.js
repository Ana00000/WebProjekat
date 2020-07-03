function addAcc(account){
	let tr = $('<tr></tr>');
	let id = $('<td>'+account.id+'</td>');
	let type = $('<td>'+account.type+'</td>');
	let nbrRooms = $('<td>'+account.nbrRooms+'</td>');
	let nbrGuests = $('<td>'+account.nbrGuests+'</td>');
	let pricePerNight = $('<td>'+account.pricePerNight+'</td>');
	let location = $('<td>' + account.location.address.street + ', ' + account.location.address.place + ', ' + account.location.address.country + '</td>');
	let forLogIn = $('<td>'+account.forLogIn+'</td>');
	let forLogOff = $('<td>'+account.forLogOff+'</td>');

		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(location).append(forLogIn).append(forLogOff);
	$('#ApartmentsTable tbody').append(tr);
}

function addComment(com, id){
	let c = $('<li> Apartment with id ' + id + ' has comment: ' + com.text + '</li>');
	$('#listComments').append(c);
}

var allQuestions = new Array();
var allComments = new Array();

$(document).ready(function() {
	    $.getJSON("apartments.json", function (data) {
	        allQuestions = data;
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,user){
		        	if(!user.status.localeCompare("ACTIVE"))
		        		addAcc(user);
		        	});
		        
		    });
	    
	    $.getJSON("apartments.json", function (data) {
	    	allComments  = data;
		    })
		    .done(function() {
		        console.log( "Another JSON loaded!" );
		        $.each( allComments, function(i,apartment){
		        	if(!apartment.status.localeCompare("ACTIVE")) {
			        	 $.each(apartment.comments, function(i, comment){
			        			if (!(comment.text == null && comment.text === "")) 
			        				addComment(comment, apartment.id);
					        });
		        	}});
		    });
});

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
	
	
$(document).ready(function() { 
    $("#gfg").on("keyup", function() { 
        var value = $(this).val().toLowerCase(); 
        $("#ApartmentsTable tr").filter(function() { 
            $(this).toggle($(this).text() 
            .toLowerCase().indexOf(value) > -1) 
        }); 
    }); 
}); 
    
    
function myFunctionNbrRooms() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputNbrRooms");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("ApartmentsTable");
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

function myFunctionNbrGuests() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputNbrGuests");
  filter = input.value.toUpperCase();
  table = document.getElementById("ApartmentsTable");
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
    	
    	
function myFunctionPrice() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInputPrice");
  filter = input.value.toUpperCase();
  table = document.getElementById("ApartmentsTable");
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

