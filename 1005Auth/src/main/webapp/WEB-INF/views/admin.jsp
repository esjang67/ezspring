<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- security에 저장된 세션정보 관련 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자만 접근 가능</h1>
	
	<p>principal : <sec:authentication property="principal"/></p>
	<p>memberVO : <sec:authentication property="principal.member"/> </p>	<!-- member : CustomUser class 에서 선언한 member -->
	<p>username : <sec:authentication property="principal.member.username"/> </p>
	<p>id : <sec:authentication property="principal.username"/> </p>
	<p>id : <sec:authentication property="principal.member.userid"/> </p>
	<p>권한 : <sec:authentication property="principal.member.authList"/> </p>
	
	<!-- hasRole('권한이름') : 해당사용자가 이 권한을 가지게 있는지 리턴 -->
	<!-- hasAnyRole('권한이름','권한이름') : 권한이름중 하나를 가지고 있는지 리턴(or) -->
	<!-- isAuthenticated() : 로그인 사용자 인지 리턴 -->
	<!-- isAnonymous()	   : 로그인 안한 사람인지 리턴 -->
	
</body>
</html>