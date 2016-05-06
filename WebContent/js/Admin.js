//window.onload = update();
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
		table.find('tr:gt(0)').remove();
		table.find('caption').remove();
		table.append('<caption>'  + '<div>Last Updated ' + new Date() + '</div>' + '</caption>');
		
		$.each(service, function(key, val) {
				//alert(service);
			table.append('<tr id="' + val.serviceId + '">'
					+'<td id="userid">' + val.userId + '</td>'
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
					+ '<td><button id="delete_' + val.serviceId + '" class="btn btn-warning btn-xs" data-toggle="confirmation" data-placement="top">Delete</button></td>'
					+ '</tr>');
			userid=val.userId;
			//alert('#delete_' + val.serviceId)
			$('#delete_' + val.serviceId).click(function(event) {
				deleteUser(val.serviceId);
			});
//			$('#delete_' + val.serviceId).confirmation({
//				onConfirm: function(event) {
//					deleteUser(val.serviceId);
//				},
//				onCancel: function(event) {}
//			});
		});
	}
}

$('#update').click(function(event) {
	update();
});

function createService() {
	var myData = new Object();
	myData.serviceName = $("#newServiceName").val();
	myData.serviceType = $("#newServiceType").val();
	myData.companyName = $("#newCompanyName").val();
	myData.address = $("#newAddress").val();
	myData.aptSuiteOther = $("#newAptSuiteOther").val();
	myData.city = $("#newCity").val();
	myData.state = $("#newState").val();
	myData.zipCode = $("#newZipCode").val();
	myData.phoneNumber = $("#newPhoneNumber").val();
	myData.emailAddress = $("#newEmailAddress").val();
	myData.desc = ($("#newDescription").val());
	myData.userId=userid;
	$.ajax({
		type : "PUT",
		url : "./rest/Users/service",
		contentType : "application/json",
		data : JSON.stringify(myData),
		success : function(data, textStatus, jqXHR) {
			update();
			generateNewUsername();
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

function deleteUser(serviceid) {
	$.ajax({
		type : "DELETE",
		url : "./rest/Users/" + serviceid,
		success : function(data, textStatus, jqXHR) {
			update();
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

$("form").submit(function(e){
	e.preventDefault();
	createService();
});

$('#username').text(Cookies.get('username'));

generateNewUsername();
update();
