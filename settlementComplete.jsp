<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset = "UTF-8"/>
	<!-- 3秒後にhome.jspに自動遷移。 -->
	<meta http-equiv = "refresh" content = "3; URL = home.jsp"/>

	<link rel = "stylesheet" href = "css/style_main.css">
	<link rel = "stylesheet" href = "css/style_complete.css">

	<title>決済完了画面</title>

</head>

<body>

	<jsp:include page = "header.jsp"/>

	<h1>決済完了画面</h1>

	<div id = "success">決済が完了しました。</div>

</body>

</html>
