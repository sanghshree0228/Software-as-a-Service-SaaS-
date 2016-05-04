$.ajax({
	type : "GET",
	url : "./rest/SessionCheck/ServiceProvider",
	contentType : "application/json",
	success : function(data, textStatus, jqXHR) {
		if (!data.sessionValid && data.redirect) {
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
var userid ;

function generateNewUsername() {
	var newUsernameInput = $('#newUsername');
	newUsernameInput.val("TestName" + Math.floor((Math.random() * 10000)));
}

function update() {
	var table = $("#mainTable");

	$.ajax({
		type : "GET",
		url : "./rest/Users/",
		success : function(data, textStatus, jqXHR) {
			updateDOM(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.responseJSON) {
				customAlert(jqXHR.responseJSON.message, true);
			} else {
				customAlert(jqXHR.status + ': ' + jqXHR.statusText, true);
			}
		}
	});

	function updateDOM(service) {
		//alert("In updateDOM")
		table.find('tr:gt(0)').remove();
//		table.find('caption').remove();
//		table.append('<caption>'  + '<div>Last Updated ' + new Date() + '</div>' + '</caption>');
//		
		$.each(service, function(key, val) {
				//alert(service);
			table.append('<tbody>'+'<tr id="' + val.serviceId + '">'
					+'<td id="userid" hidden=true>' + val.userId + '</td>'
					+ '<td>' + val.serviceId + '</td>'
					+ '<td>' + val.serviceName + '</td>'
					+ '<td>' + val.serviceType + '</td>'
					+ '<td>' + val.companyName + '</td>'
					+ '<td>' + val.address + '</td>'
					+ '<td>' + (val.aptSuiteOther || '') + '</td>'
					+ '<td>' + val.city + '</td>'
					+ '<td>' + val.state + '</td>'
					+ '<td>' + val.zipCode + '</td>'
					+ '<td>' + val.phoneNumber + '</td>'
					+ '<td>' + (val.emailAddress || '') + '</td>'
					+ '<td>' + val.desc + '</td>'
					+ '<td><button id="enroll_' + val.serviceId + '" class="btn btn-warning btn-xs" data-toggle="confirmation" data-placement="top">Register</button></td>'
					+ '</tr>'+'<tbody>');
			//userid=val.userId;
			//alert('#delete_' + val.serviceId)
			$('#enroll_' + val.serviceId).click(function(event) {
				enrollUser(val.serviceId);
			});
		    
		    
		});
	}
}

$('#update').click(function(event) {
	update();
});


function enrollUser(serviceid) {
	$.ajax({
		type : "PUT",
		url : "./rest/Users/" + serviceid,
		success : function(data, textStatus, jqXHR) {
			//update();
			
			customAlert(data.message);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.responseJSON) {
				customAlert(jqXHR.responseJSON.message, true);
			} else {
				customAlert(jqXHR.status + ': ' + jqXHR.statusText, true);
			}
		}
	});
}


$('#username').text(Cookies.get('username'));

generateNewUsername();
update();
