$(document).ready(function() {
	$('button#logging').click(function() {
		
		let flag = true;
		let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		let name=$('input[name=name]').val();
		let surname=$('input[name=surname]').val();
		let gender=$("select#gender option:checked" ).val();
		let role="GUEST";
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[5]).remove();
		}
		
		if (username == null || username === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Neispravan unos</td>');
			tr.append(td);
			flag = false;
		}
		
		if (password == null || password === "") {
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Neispravan unos</td>');
			tr.append(td);
			flag = false;
		}
		

		if (name == null || name === "") {
			let tr = $($('table tbody tr')[2]);
			let td = $('<td>Neispravan unos</td>');
			tr.append(td);
			flag = false;
		}
		
		if (surname == null || surname === "") {
			let tr = $($('table tbody tr')[3]);
			let td = $('<td>Neispravan unos</td>');
			tr.append(td);
			flag = false;
		}

		if (gender == null || gender === "") {
			let tr = $($('table tbody tr')[4]);
			let td = $('<td>Neispravan unos</td>');
			tr.append(td);
			flag = false;
		}
		
		if(flag){
			$.post({
				url: 'rest/login',
				data: JSON.stringify({username: username, password: password, name: name, surname: surname, gender: gender, role: role}),
				contentType: 'application/json',
				success: function() {
					window.location.href= 'page.html';
				},
				error: function(message){
					let name = message.responseText;
					$('p#error').text(name);
					$('p#error').css('color','red');
					$('p#error').show();
				}
			});
		}
	});
});