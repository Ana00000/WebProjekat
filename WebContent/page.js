function addAcc(account){
    let tr = $('<tr></tr>');
    let tdHealthInsuranceNbr = $('<td>'+account.healthInsuranceNbr+'</td>');
    let tdName = $('<td>'+account.name+'</td>');
    let tdLastname = $('<td>'+account.lastname+'</td>');
    let tdDateOfBirth =  $('<td>'+account.dateOfBirth+'</td>');
    let tdGender = $('<td>'+account.gender+'</td>');
    let tdHealthStatus = $('<td>'+account.healthStatus+'</td>');
    let tdTest = $('<td></td>');

    tr.append(tdHealthInsuranceNbr).append(tdName).append(tdLastname).append(tdDateOfBirth).append(tdGender).append(tdHealthStatus).append(tdTest);
    $('#tbPatients tbody').append(tr);
}

$(document).ready(function() {
    $('a[href=off]').click(function(event) {
        event.preventDefault();
        $.get({
            url : 'rest/logout',
            success : function() {
                window.location.href = 'login.html';
            }
        });
    });
    
    $.get({
        url: 'rest/loggedUser',
        success: function(user) {
        	
        	console.log(user)
        	
        	let name = $("#name").val(user.name)
            let username = $("#username").val(user.username)
            let password = $("#password").val(user.password)
            let passwordDouble = $("#passwordDouble").val(user.password)
            let surname = $("#surname").val(user.surname)
            let gender=$("#gender").val(user.gender.toString());
        }
    });
    
	$('button#change').click(function() {
		
		let flag = true;
		let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let passwordDouble=$('input[name=passwordDouble]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role="GUEST";
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[6]).remove();
		}
		
		if (username == null || username === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Username shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(username.length<6){
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Username is too short ,it needs at least 6 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(username.length>30){
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Username is too long ,the limit is  30 characters. </td>');
			tr.append(td);
			flag = false;
		}
			
		
		if (password == null || password === "") {
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Password shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(password.length<6){
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Password is too short ,it needs at least 6 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(password.length>30){
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Password is too long ,the limit is  30 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (passwordDouble == null || passwordDouble === "") {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Password check shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if (!(passwordDouble === password)) {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Passwords aren\'t matching. </td>');
			tr.append(td);
			flag = false;
		}
		

		if (name == null || name === "") {
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Name shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length<2){
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Name is not valid ,it needs at least 2 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(name.length>30){
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>You reached the limit which is  30 characters. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (surname == null || surname === "") {
			let tr = $($('table tbody tr')[4]);
			let td = $('<td>Surname shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}else if(surname.length<2){
			let tr = $($('table tbody tr')[4]);
			let td = $('<td>Surname is not valid ,it needs at least 2 characters. </td>');
			tr.append(td);
			flag = false;
		}else if(surname.length>50){
			let tr = $($('table tbody tr')[4]);
			let td = $('<td>You reached the limit which is  50 characters. </td>');
			tr.append(td);

		}
		
		if(flag){
			$.ajax({
				url: 'rest/changeData',
				data: JSON.stringify({username: username, password: password, name: name, surname: surname, gender: gender, role: role}),
				contentType: 'application/json',
				type: 'PUT',
				success: function() {
					alert('Your changes have been saved successfully.');
					window.location.href= 'page.html';
				},
				error: function(message){
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
				}
				
			});
		}
	});
});
