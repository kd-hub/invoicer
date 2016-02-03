<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Klienci <small>Lista klientów</small>
					</h1>
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
									<td><a
										href="<c:url value='/customer/edit/${customer.id}' />">edytuj</a></td>
									<td><a
										href="<c:url value='/customer/remove/${customer.id}' />">usuń</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<a href="<c:url value='/customer/add'/>"
						class="btn btn-info pull-right">Dodaj nowego klienta</a>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>