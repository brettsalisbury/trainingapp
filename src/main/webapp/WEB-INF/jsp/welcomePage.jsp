<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>Training app | Welcome Page</title>

<!-- Framework CSS -->
<link rel="stylesheet" href="resources/css/blueprint/screen.css"
	type="text/css" media="screen, projection" />
<link rel="stylesheet" href="resources/css/blueprint/print.css"
	type="text/css" media="print" />
<!--[if IE]><link rel="stylesheet" href="resources/css/blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->

<!-- Import fancy-type plugin. -->
<link rel="stylesheet"
	href="resources/css/blueprint/plugins/fancy-type/screen.css"
	type="text/css" media="screen, projection" />

</head>
<body>
	<div class="container">
		<h1>This is a really dodgy training app - Welcome!</h1>
		<hr/>
		<div class="span-5 colborder">
			 <h2>Current users:</h2>
                <table id="currentUsers">
                    <c:forEach var="userName" items="${welcomePageModel.userNames}">
                    <tr>
                        <td id="userName">${userName}</td>
                    </tr>
                    </c:forEach>
                </table>
		</div>
		<div class="span-12 colborder">
			Click <a href="newContact" id="newContactNav">here</a> to add a new contact.
		</div>
		<div class="span-5 last">Right sidebar</div>
	</div>
</body>
</html>