function addAcc(account){
	let tr = $('<tr></tr>');
	let username = $('<td>'+account.username+'</td>');
	let name = $('<td>'+account.name+'</td>');
	let surname = $('<td>'+account.surname+'</td>');
	let role = $('<td>'+account.role+'</td>');
	let gender = $('<td>'+account.gender+'</td>');
	let password = $('<td>'+account.password+'</td>');
	let tdTest = $('<td></td>');

	tr.append(username).append(name).append(surname).append(role).append(gender).append(password).append(tdTest);
	$('#UsersTable tbody').append(tr);
}

var allQuestions = new Array();

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
});

