<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>Training app | New Contact</title>

<%@ include file="css_imports.jsp"%>

</head>
<body>
	<div class="container">
		<h1>Add a new user</h1>
		<hr />
		<div class="span-22 last">
			<form:form commandName="newContactFormModel">
				<fieldset>
					<table>
						<tr>
							<td><label for="name">Name:</label></td>
							<td><form:input path="name" /></td>
						</tr>
						<tr>
							<td><label for="age">Age:</label></td>
							<td><form:input path="age" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Add User" /></td>
						</tr>
					</table>
				</fieldsert>
			</form:form>
		</div>
	</div>
</body>
</html>