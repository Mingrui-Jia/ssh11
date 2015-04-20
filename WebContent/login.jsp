<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User login</title>
</head>
<body>
<script type="text/javascript">
function checkUser(){
	var form=document.forms[0];
	form.action="/ssh1/user/checkUser";
	form.method="get";
	form.submit();
	
}

</script>
</head>
<body>
	<form name="userForm" action="">
		name:<input type="text" name="userName"> 
		password:<input type="text" name="password">
		
		<input type="button" value="add" onclick="checkUser()">
	</form>
	<h2>login</h2>


</body>
</body>
</html>