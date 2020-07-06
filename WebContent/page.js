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
        	
        	$("#name").val(user.name)
            $("#username").val(user.username)
            $("#password").val(user.password)
            $("#passwordDouble").val(user.password)
            $("#surname").val(user.surname)
            $("#gender").val(user.gender.toString());
        	$("#role").val(user.role.toString());
        	
        }
    });
    
    $('button#apartmentGo').click(function() {
    	
    	let role=$("select#role option:checked" ).val();
    	if(!role.localeCompare("GUEST"))
    		window.location.href= ' apartments.html';
    	else
    		alert("Access denied, you are not a guest!");
	});
    
    $('button#infoHost').click(function() {
    	
    	let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role=$("select#role option:checked" ).val();
    	
		$.post({
			url: 'rest/infoHost',
			data: JSON.stringify({username: username, password: password, name: name, surname: surname, gender: gender, role: role}),
			contentType: 'application/json',
			success: function() {
				window.location.href= 'pagehost.html';
			},
			error:function(message){
			if(message.status==403)
				alert("Access denied, you are not a host!!");
			}
		});
	});
    
    
    
    $('button#guestReservations').click(function() {
    	
    	let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role=$("select#role option:checked" ).val();
    	
		$.post({
			url: 'rest/guestReservations',
			data: JSON.stringify({username: username, password: password, name: name, surname: surname, gender: gender, role: role}),
			contentType: 'application/json',
			success: function() {
				window.location.href= 'guestReservations.html';
			},
			error:function(message){
				if(message.status==403)
					alert("Access denied,you are not a guest!");
				}
		});
	});
    
    
    
    $('button#infoAdmin').click(function() {
    	
    	let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role=$("select#role option:checked" ).val();
    	
		$.post({
			url: 'rest/infoAdmin',
			data: JSON.stringify({username: username, password: password, name: name, surname: surname, gender: gender, role: role}),
			contentType: 'application/json',
			success: function() {
				window.location.href= 'pageadmin.html';
			},
			error:function(message){
				if(message.status==403)
					alert("Access denied, you are not an admin!");
				}
		});
	});
    
	$('button#change').click(function() {
		
		let flag = true;
		let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let passwordDouble=$('input[name=passwordDouble]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role=$("select#role option:checked" ).val();
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[7]).remove();
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
