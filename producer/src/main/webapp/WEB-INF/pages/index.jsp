<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ActiveMQ Sender</title>
    <spring:url value="/resources/js/jquery-3.1.1.min.js"
    	var="jqueryJs" />
    <script src="${jqueryJs}"></script>
</head>
<body>
<h1>Welcome to ActiveMQ Sender Application</h1>
</body>
<p>Send message to:</p>
<input type='button' onclick="sendMessage('topic')" value="Topic">
<input type='button' onclick="sendMessage('queue')" value="Queue">
<script>
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
</script>
</html>