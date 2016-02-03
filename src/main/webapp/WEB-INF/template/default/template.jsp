<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link href="<c:url value="/resources/css/AdminLTE.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/_all-skins.min.css" />"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<title>Invoicer</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
	<!-- jQuery 2.1.4 -->
	<script src="<c:url value="/resources/js/jQuery-2.1.4.min.js" />"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<!-- SlimScroll -->
	<script src="<c:url value="/resources/js/jquery.slimscroll.min.js" />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/js/app.min.js" />"></script>
	<!-- jQuery UI -->
 	<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
</body>
<tiles:insertAttribute name="scripts" />
</html>