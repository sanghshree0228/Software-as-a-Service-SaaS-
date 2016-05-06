$.ajax({
	type : "GET",
	url : "./rest/SessionCheck/",
	contentType : "application/json",
	success : function(data, textStatus, jqXHR) {
		if (data.sessionValid && data.redirect) {
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

$("form").submit(function(e){
	e.preventDefault();
	var user = {
			username: $('#username').val(),
			password: $('#password').val(),
			firstName: $('#firstName').val(),
			middleName: $('#middleName').val(),
			lastName: $('#lastName').val(),
			role:$('#registeras').val() || ('#registeras1').val(),
			address: $('#address').val(),
			aptSuiteOther: $('#aptSuiteOther').val(),
			city: $('#city').val(),
			state: $('#state').val(),
			zipCode: $('#zipCode').val(),
			phoneNumber: $('#phoneNumber').val(),
			emailAddress: $('#emailAddress').val(),
			birthDate: new Date($('#birthDate').val())
	};
	$.ajax({
		type : "PUT",
		url : "./rest/Users/",
		contentType : "application/json",
		data : JSON.stringify(user),
		success : function(data, textStatus, jqXHR) {
			$("form").prop('hidden', true);
			customAlert(data.message);
			var alertDiv = $('#alert');
			alertDiv.append('<a href="./login.html">Click Here to Sign In</a>');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.responseJSON) {
				customAlert(jqXHR.responseJSON.message);
			} else {
				customAlert(jqXHR.statusText, true);
			}
		}
	});
});
