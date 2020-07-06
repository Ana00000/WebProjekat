function addComment(com, idComment, apId){
	let c = $('<li><p> Apartment with id ' + apId + ' has comment: ' + com+'   <button class="btnSelect" onclick="funkcija('+idComment+')">Disapprove</button></p></li>' );
	$('#listCommentsHost').append(c);
}

var allComments = new Array();
var host;
var comments = new Array();
var oneComment;
var oneCommentId;
var idComment;

function getComments(id) {
	oneComment = "no comment";
	oneCommentId = 0;
	$.each(comments, function(i,comment){
	   	if(!comment.id.localeCompare(id)){
	   		oneComment = comment.text;
	   		oneCommentId = comment.id;
	   	}
   	});
}

function funkcija(id)
{
	idComment = id;
	
	$.ajax({
		url: 'rest/comments/approvalComment',
		data: JSON.stringify({id: idComment, visible: "false"}),
		contentType: 'application/json',
		type:'PUT',
		success: function() {
			alert("Comment successfully approved!");
			window.location.href= 'hostComments.html';
		},
		error: function(message){
			if(message.status==400)
				alert("Comment unsuccessfully approved!");
			let name = message.responseText;
			$('p#error').text(name);
			$('p#error').css('color','red');
		}
	});
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
	        	if(!apartment.host.username.localeCompare(host.username) && ! apartment.alive.localeCompare("true")){
		        	 $.each(apartment.comments, function(a, comment){
		        		 	getComments(comment.id);
		        			if (oneComment.localeCompare("no comment")) 
		        				addComment(oneComment, oneCommentId, apartment.id);
				        });
		    	}});
		});
		
});
