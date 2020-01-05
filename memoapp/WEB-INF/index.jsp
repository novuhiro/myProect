<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>メモ登録画面</title>
</head>
<% String first_name = (String) request.getAttribute("first_name"); %>
<% String last_name = (String) request.getAttribute("last_name"); %>
<body>
<h1>メモ登録画面</h1>
<p><%= first_name %> サン</p>
<p><%= last_name %> サン</p>
<form method="post">
	<input type="text" name="title" size="50"/><br />
	<textarea rows="5" cols="80" name="memo"></textarea>
	<br /><input type="submit" />

</form>
</body>
</html>