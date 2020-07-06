function addAmenity(amenity){
	let tr = $('<tr></tr>');
	let id = $('<td>'+amenity.id+'</td>');
	let name = $('<td>'+amenity.name+'</td>');
	let btnSelect =$('<td><button class="btnSelectEdit">Edit</button>   <button class="btnSelectDelete">Delete</button></td>');
	
	tr.append(id).append(name).append(btnSelect);
	$('#AmenitiesTable tbody').append(tr);
}

var allAmenities = new Array();
var id;
var name;
var amenityList=new Array();
var allApartments=new Array();

$(document).ready(function() {
    $.getJSON("amenities.json", function (data) {
    	allAmenities = data;
	    })
	    .done(function() {
	        console.log( "JSON loaded!" );
	        $.each( allAmenities, function(i,amenity){
	        		if(amenity.alive)
	        			addAmenity(amenity);
	        	});
	        
	    });
    
    $("#AmenitiesTable").on('click','.btnSelectEdit',function(){
        var currentRow = $(this).closest("tr"); 
        var col1 = currentRow.find("td:eq(0)").text();
        var col2 = currentRow.find("td:eq(1)").text();
        
    	$("#id").val(col1)
    	$("#name").val(col2)
   });
    
    $("#AmenitiesTable").on('click','.btnSelectDelete',function(){
        var currentRow = $(this).closest("tr"); 
        var col1 = currentRow.find("td:eq(0)").text();
        var col2 = currentRow.find("td:eq(1)").text();
        
        id = col1;
        name = col2;
        
        $.ajax({
			url: 'rest/amenities/deleteAmenity',
			data: JSON.stringify({id: id, name: name}),
			contentType: 'application/json',
			type:'DELETE',
			success: function() {
				alert("Amenity successfully removed!");
				window.location.href= 'amenity.html';
				
				 $.getJSON("apartments.json", function (data) {
				    	
				    	allApartments = data;
				  
					    })
					    .done(function() {
					        console.log( "JSON loaded!" );
					        $.each(allApartments, function(i,apartment){
					  	        		addAliveAmenity(apartment.amenities);
					        	});
					 });
			},
			error: function(message){
				let name = message.responseText;
				$('p#error').text(name);
				$('p#error').css('color','red');
			}
		});
   });
    
    $('button#addAmenity').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let name=$('input[name=name]').val();
		
		for (i = 0; i < $('#changeTable tbody tr').length; i++) {
			let tr = $($('#changeTable tbody tr')[i]);
			$(tr.children()[2]).remove();
		}
		
		if (id == null || id === "") {
			let tr = $($('#changeTable tbody tr')[0]);
			let td = $('<td>Id shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(id.length>20){
			let tr = $($('#changeTable tbody tr')[0]);
			let td = $('<td>Id is too long ,the limit is 20 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (name == null || name === "") {
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>Name shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length<2){
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>Name is not valid ,it needs at least 2 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length>20){
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>You reached the limit which is 20 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if(flag){
			$.ajax({
				url: 'rest/amenities/addAmenity',
				data: JSON.stringify({id: id, name: name}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					alert("Amenity successfully added!");
					window.location.href= 'amenity.html';
				},
				error: function(message){
					if(message.status==400)
						alert("Id of an amenity already exists!");
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
			});
		}
	});
    
    function addAliveAmenity(){
		function setAmenity(amenities) {
			$.each(amenities, function(i,amenity)
		  {
				if(amenity.alive)
					amenityList.push(amenity);
		  });
	}
	setAmenity(selectedApartment.comments);
	selectedApartment.comments=comList;
	
		$.ajax({
			url: 'rest/apartments/updateApartmentComments',
			data: JSON.stringify({id: selectedApartment.id,comments:selectedApartment.comments}),
			contentType: 'application/json',
			type:'PUT',
			success: function() {
				alert("Apartment successfully updated!");
				window.location.href= 'apartments.html';
			},
			error: function(message){
				if(message.status==400)
					alert("Update was unsuccessful!");
				let name = message.responseText;
				$('p#error').text(name);
				$('p#error').css('color','red');
			}
		});

}
    
    $('button#setAmenity').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let name=$('input[name=name]').val();
		
		for (i = 0; i < $('#changeTable tbody tr').length; i++) {
			let tr = $($('#changeTable tbody tr')[i]);
			$(tr.children()[2]).remove();
		}
		
		if (id == null || id === "") {
			let tr = $($('#changeTable tbody tr')[0]);
			let td = $('<td>Id shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(id.length>20){
			let tr = $($('#changeTable tbody tr')[0]);
			let td = $('<td>Id is too long ,the limit is 20 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (name == null || name === "") {
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>Name shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length<2){
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>Name is not valid ,it needs at least 2 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length>20){
			let tr = $($('#changeTable tbody tr')[1]);
			let td = $('<td>You reached the limit which is 20 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if(flag){
			$.ajax({
				url: 'rest/amenities/setAmenity',
				data: JSON.stringify({id: id, name: name}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					alert("Amenity successfully changed!");
					window.location.href= 'amenity.html';
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

function sortTable(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("AmenitiesTable");
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
	  table = document.getElementById("AmenitiesTable");
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
	  table = document.getElementById("AmenitiesTable");
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

function myFunctionName() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInputName");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("AmenitiesTable");
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