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


function generateNewUsername() {
	var newUsernameInput = $('#newUsername');
	newUsernameInput.val("TestName" + Math.floor((Math.random() * 10000)));
}

/**
 * 
 */
function updatetable() {
	var mytable = $("#myTable");

	$.ajax({
		type : "GET",
		url : "./rest/Users/service",
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
		mytable.find('tr:gt(0)').remove();
		mytable.find('caption').remove();
		mytable.append('<caption>'  + '<div>Last Updated ' + new Date() + '</div>' + '</caption>');
		
		$.each(service, function(key, val) {
				//alert(service);id="' + val.serviceId + '"
			mytable.append('<tr >'
					+'<td>' + val.ServiceName + '</td>'
					+ '<td>' + val.ServiceType + '</td>'
					+ '<td>' + val.FirstName + '</td>'
					+ '<td>' + val.LastName + '</td>'
					+ '<td>' + val.PhoneNumber + '</td>'
					+ '<td>' + val.Email + '</td>'
					
					+ '</tr>');
//			userid=val.userId;
//			//alert('#delete_' + val.serviceId)
//			$('#delete_' + val.serviceId).click(function(event) {
//				deleteUser(val.serviceId);
//			});
//			$('#delete_' + val.serviceId).confirmation({
//				onConfirm: function(event) {
//					deleteUser(val.serviceId);
//				},
//				onCancel: function(event) {}
//			});
		});
	}
	
}
updatetable();
