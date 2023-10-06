<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>로그인 페이지</h1>
	<h1>${error}</h1>
	<h1>${logout}</h1>
	<form method="post" action="/login">
		id : <input type="text" name="username"><br>
		pw : <input type="password" name="password"><br>
		<input type="checkbox" name="remember-me">자동로그인<br>
		<input type="submit" value="로그인">
		<input type="text" name="${_csrf.parameterName}" value="${_csrf.token}">	
	</form>
	
</body>
</html>