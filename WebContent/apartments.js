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
	let allAmenities = [];
	allAmenities = $('<td></td>');
	
	
	$.each(account.amenities, function(i,amenity){
		if(amenity.alive)
			allAmenities.append(amenity.name);
		
		allAmenities.append(" ");
   	});
	
	let btnSelect =$('<td><button class="btnSelect">Make a reservation</button></td>');
	getReservation(account.id);
	
	if(!oneReservation.localeCompare("REJECTED"))
		{
			let btnComment =$('<td><button class="btnComment">Make a comment</button></td>');
			tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(location).append(forLogIn).append(forLogOff).append(allAmenities).append(btnSelect).append(btnComment);
			$('#ApartmentsTable tbody').append(tr);
	}
	else if(!oneReservation.localeCompare("FINISHED"))
	{
		let btnComment =$('<td><button class="btnComment">Make a comment</button></td>');
		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(location).append(forLogIn).append(forLogOff).append(allAmenities).append(btnSelect).append(btnComment);
		$('#ApartmentsTable tbody').append(tr);
	}	
	else{
		tr.append(id).append(type).append(nbrRooms).append(nbrGuests).append(pricePerNight).append(location).append(forLogIn).append(forLogOff).append(allAmenities).append(btnSelect);
		$('#ApartmentsTable tbody').append(tr);
	}
	
}
	
	

function addComment(com, id){
	let c = $('<li> Apartment with id ' + id + ' has comment: ' + com + '</li>');
	$('#listComments').append(c);
}



var allQuestions = new Array();
var allComments = new Array();
var oneComment;
var allReservations = new Array();
var comments = new Array();
var oneReservation;

function getComments(id) {
	oneComment = "no comment";
	$.each(comments, function(i,comment){
	   	if(!comment.id.localeCompare(id))
	   		oneComment = comment;
   	});
}

function getReservation(id) {
	oneReservation = "no comment";
	$.each(allReservations, function(i,reservation){
	   	if(!reservation.rented.localeCompare(id))
	   		{
	   			oneReservation = reservation.status;
	   		}
	   	
	   	
   	});
}

$(document).ready(function() {
	
	    $.getJSON("reservations.json", function (data) {
	    	allReservations = data;
		});
	    $.getJSON("apartments.json", function (data) {
	    	
	        allQuestions = data;
	  
		    })
		    .done(function() {
		        console.log( "JSON loaded!" );
		        $.each( allQuestions, function(i,user){
		        	if(!user.status.localeCompare("ACTIVE") && !user.alive.localeCompare("true"))
		        		addAcc(user);
		        	});
		        
		 });
	   
	    $.getJSON("comments.json", function (data) {
	    	comments = data;
		});
	    

	    
	    $.getJSON("apartments.json", function (data) {
	    	allComments  = data;
		    })
		    .done(function() {
		        console.log( "Another JSON loaded!" );
		        $.each(allComments, function(i,apartment){
		        	if(!apartment.status.localeCompare("ACTIVE") && !apartment.alive.localeCompare("true")) {
			        	 $.each(apartment.comments, function(i, comment){
			        		 	getComments(comment.id);
			        			if (oneComment.text.localeCompare("no comment")) 
			        				if(oneComment.visible)
			        					addComment(oneComment.text, apartment.id);
					        });
		        }});
		    });
	    
	    $("#ApartmentsTable").on('click','.btnSelect',function(){
            var currentRow=$(this).closest("tr"); 

            var id=currentRow.find("td:eq(0)").text();

          $.ajax({
           url: 'rest/apartments/selectedApartment',
           data: JSON.stringify({id: id}),
           contentType: 'application/json',
           type:'PUT',
           success: function() {
               console.log("App set");
               window.location.href= 'addReservation.html';
           },
           error: function(){
               console.log("App not set");
           }
            });

       });
	    
	    
	    $("#ApartmentsTable").on('click','.btnComment',function(){
            var currentRow=$(this).closest("tr"); 

            var id=currentRow.find("td:eq(0)").text();
            
            $.ajax({
                url: 'rest/apartments/selectedApartment',
                data: JSON.stringify({id: id}),
                contentType: 'application/json',
                type:'PUT',
                success: function() {
                    console.log("App set");
                    window.location.href= 'addComment.html';
                },
                error: function(){
                    console.log("App not set");
                }
                 });


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
