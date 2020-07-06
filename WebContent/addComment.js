var guestCurrent;
var selectedApartment;
var success=false;
var oneComment;

var comList=new Array();

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
                selectedApartment=apartment;
            }
        });
	
	$('button#addingCom').click(function() {
		
		let flag = true;
		let id=$('input[name=id]').val();
		let apartment=selectedApartment.id;
		let text=$('input[name=text]').val();
		let grade=$('input[name=grade]').val();
		let guest = guestCurrent;
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[3]).remove();
		}
		
		if (id == null || id === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Id shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (text == null || text === "") {
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Comment shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (grade == null || grade === "") {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Rating shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(grade>5 || grade<1)
		{
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Rating should be between 1 and 5. </td>');
			tr.append(td);
			flag = false;
		}

		
		if(flag){

			$.ajax({
				
				url: 'rest/comments/addComment',
				data: JSON.stringify({id: id, guest: guest, apartment: apartment, text: text, grade: grade}),
				contentType: 'application/json',
				type:'PUT',
				success: function() {
					setApartment();
				},
				error: function(message){
					if(message.status==400)
						alert("Comment already exists!");
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
				
			});
		}
		
		
	function setApartment(){
				function setComments(comments) {
					$.each(comments, function(i,comment)
				 {
				   			comList.push(comment);
				  });
				comList.push(id);
			}
			setComments(selectedApartment.comments);
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
			
			
	});
});