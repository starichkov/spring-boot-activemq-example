<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ActiveMQ Producer</title>
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery-3.6.4.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/messages.js"/>"></script>
</head>
<body>
<h1>ActiveMQ Producer</h1>
<p>Send message to:</p>
<button type='button' class="btn btn-primary" onclick="sendMessage('topic')">Topic</button>
<button type='button' class="btn btn-success" onclick="sendMessage('queue')">Queue</button>
</body>
</html>