<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload</title>
</head>
<body>
	<form method="post" action="uploadAction" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple>
		
		<input type="submit" value="전송">
	</form>
</body>
</html>