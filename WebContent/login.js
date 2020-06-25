$(document).ready(function() {
	$('button#log').click(function() {
		
		let flag = true;
		let username=$('input[name=username]').val();
		let password=$('input[name=password]').val();
		
		for (i = 0; i < $('table tbody tr').length; i++) {
			let tr = $($('table tbody tr')[i]);
			$(tr.children()[2]).remove();
			$(tr.children()[3]).remove();
		}
		
		if (username == null || username === "") {
			let tr = $($('table tbody tr')[0]);
			let td = $('<td>Username shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		if (password == null || password === "") {
			let tr = $($('table tbody tr')[1]);
			let td = $('<td>Password shouldn\'t be empty. </td>');
			tr.append(td);
			flag = false;
		}
		
		
		if(flag){
			$.post({
				url: 'rest/login',
				data: JSON.stringify({username: username, password: password}),
				contentType: 'application/json',
				success: function() {
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