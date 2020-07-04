function addComment(com, id){
	let c = $('<li><p> Apartment with id ' + id + ' has comment: ' + com.text+'   <button class="btnSelect" onclick="funkcija('+com.id+')">Approve</button></p></li>' );
	$('#listCommentsHost').append(c);
}

var allComments = new Array();

var host;

$(document).ready(function() {
	
	$.get({
	    url: 'rest/loggedUser',
	    success: function(user) {
	    	host = user;
	    }
	});
	
	
		$.getJSON("apartments.json", function (data) {
		allComments  = data;
			})
		.done(function() {
		    console.log( "Another JSON loaded!" );
		    $.each( allComments, function(b,apartment){
		    	if(!apartment.host.username.localeCompare(host.username)) {
		        	 $.each(apartment.comments, function(a, comment){
		        			if (!(comment.text == null && comment.text === "")) 
		        				addComment(comment, apartment.id);
				        });
		    	}});
		});
		
		

		
});

function funkcija(id)
{
	alert(id);
}