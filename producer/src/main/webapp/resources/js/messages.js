function sendMessage(target) {
	$.ajax({
		type : "POST",
		url : "/" + target,
		timeout : 100000,
		success : function(data) {
			console.log("Message sent to '" + target + "'");
		}
	});
}