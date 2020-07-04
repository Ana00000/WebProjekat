function addComment(com, id, apId){
	let c = $('<li><p> Apartment with id ' + apId + ' has comment: ' + com+'   <button class="btnSelect" onclick="funkcija('+id+')">Approve</button></p></li>' );
	$('#listCommentsHost').append(c);
}

var allComments = new Array();
var host;
var comments = new Array();
var oneComment;
var oneCommentId;

function getComments(id) {
	oneComment = "no comment";
	oneCommentId = 0;
	$.each(comments, function(i,comment){
	   	if(comment.id == id){
	   		oneComment = comment.text;
	   		oneCommentId = comment.id;
	   	}
   	});
}

function funkcija(id)
{
	alert(id);
}

$(document).ready(function() {
	
		$.get({
		    url: 'rest/loggedUser',
		    success: function(user) {
		    	host = user;
		    }
		});
	
		 $.getJSON("comments.json", function (data) {
		    	comments = data;
		});
		
		$.getJSON("apartments.json", function (data) {
			allComments  = data;
			})
		.done(function() {
		    console.log( "Another JSON loaded!" );
		    $.each( allComments, function(b,apartment){
	        	if(!apartment.host.username.localeCompare(host.username)){
		        	 $.each(apartment.comments, function(a, comment){
		        		 	getComments(comment.id);
		        			if (oneComment.localeCompare("no comment")) 
		        				addComment(oneComment, oneCommentId, apartment.id);
				        });
		    	}});
		});
});