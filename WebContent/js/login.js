$("login-check").submit(function(e){
	e.preventDefault();
	var user = {
			username: $('#username').val(),
			password: $('#password').val()
	};
	$.ajax({
		type : "POST",
		url : "./rest/logincheck/",
		contentType : "application/json",
		data : JSON.stringify(user),
		success : function(data, textStatus, jqXHR) {
			if (data.redirect) {
				window.location.href = data.redirectURL;
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.responseJSON) {
				customAlert(jqXHR.responseJSON.message, true);
			} else {
				customAlert(jqXHR.status + ': ' + jqXHR.statusText, true);
			}
		}
	});
});
