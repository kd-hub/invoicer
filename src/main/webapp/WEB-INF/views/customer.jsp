<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Invoicer</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a href="#" class="navbar-brand"><b>Invoicer</b></a>
					</div>
					<div class="collapse navbar-collapse pull-left"
						id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Klienci <span
									class="sr-only">(current)</span></a></li>
							<li><a href="#">Magazyn</a></li>
							<li><a href="#">Faktury</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Raporty<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Raport 1</a></li>
									<li><a href="#">Raport 2</a></li>
									<li><a href="#">Raport 3</a></li>
									<li class="divider"></li>
									<li><a href="#">Raport sprzedaży</a></li>
									<li class="divider"></li>
									<li><a href="#">Raport magazynowy</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<div class="content-wrapper">
			<div class="container">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
						Klienci <small>Lista</small>
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li><a href="#">Layout</a></li>
						<li class="active">Top Navigation</li>
					</ol>
				</section>
				<section class="content">
					<c:if test="${!empty customersList}">
						<table class="table table-striped table-hover">
							<tr>
								<th>Nazwa firmy</th>
								<th>NIP</th>
								<th>Ulica</th>
								<th>Nr domu</th>
								<th>Nr lokalu</th>
								<th>Kod pocztowy</th>
								<th>Miasto</th>
								<th>Edytuj</th>
								<th>Usuń</th>
							</tr>
							<c:forEach items="${customersList}" var="customer">
								<tr>
									<td>${customer.companyName}</td>
									<td>${customer.vatIdNumber}</td>
									<td>${customer.streetName}</td>
									<td>${customer.streetNumber}</td>
									<td>${customer.apartmentNumber}</td>
									<td>${customer.postalCode}</td>
									<td>${customer.cityName}</td>
									<td><a href="<c:url value='/edit/${customer.id}' />">edytuj</a></td>
									<td><a href="<c:url value='/delete/${customer.id}' />">usuń</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<button type="button" class="btn btn-info pull-right"
						style="margin-right: 5px;">Dodaj nowego klienta</button>
				</section>
			</div>
		</div>
		<footer class="main-footer">
			<div class="container">
				<div class="pull-right hidden-xs">Version 0.0.1</div>
				Copyright &copy; 2015 Karol Dobrowolski. All rights reserved.
			</div>
		</footer>
	</div>
	<!-- jQuery 2.1.4 -->
	<script src="<c:url value="/resources/js/jQuery-2.1.4.min.js" />"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<!-- SlimScroll -->
	<script src="<c:url value="/resources/js/jquery.slimscroll.min.js" />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/js/app.min.js" />"></script>
</body>
</html>
