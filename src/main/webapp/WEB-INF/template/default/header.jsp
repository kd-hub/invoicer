<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="main-header">
	<nav class="navbar navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/invoicer" class="navbar-brand"><b>Invoicer</b></a>
			</div>
			<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<!-- 
					<c:out
						value="${requestScope['javax.servlet.forward.servlet_path']}" />
 -->
					<li class="active"><a href="<c:url value='/customer/list' />">Klienci</a></li>
					<li><a href="<c:url value='/product/list' />">Produkty</a></li>
					<li><a href="<c:url value='/invoice/list' />">Faktury</a></li>
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
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Ustawienia<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<c:url value='/vatrate/list' />">Stawki VAT</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
</header>