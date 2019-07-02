<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset = "UTF-8"/>
	<!-- 3秒後にHomeActionに自動遷移。 -->
	<meta http-equiv = "refresh" content = "3; URL = HomeAction"/>

	<link rel = "stylesheet" href = "css/style_main.css">

	<title>セッションエラー画面</title>

</head>

<body>

	<h1 class = "error">セッションエラー画面</h1>

	<div class = "errormessage errorLeft">ログインしていないか、タイムアウトしました。</div>

</body>

</html>
