<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Faktury <small>Lista faktur</small>
					</h1>
				</section>
				<section class="content">
					<c:if test="${!empty invoicesList}">
						<table class="table table-striped table-hover">
							<tr>
								<th>Numer</th>
								<th>Nazwa kontrahenta</th>
								<th>Data sprzedaży</th>
								<th>Data wystawienia</th>
								<th>Termin płatności</th>
								<th>Kwota netto</th>
								<th>Kwota brutto</th>
								<th>Wyświetl</th>
							</tr>
							<c:forEach items="${invoicesList}" var="invoice">
								<tr>
									<td>${invoice.id}</td>
									<td>${invoice.customer.companyName}</td>
									<td>${invoice.dateOfSale}</td>
									<td>${invoice.dateOfIssue}</td>
									<td>${invoice.paymentDueDate}</td>
									<td>${invoice.totalAmountNet}</td>
									<td>${invoice.totalAmountGross}</td>
									<td><a
										href="<c:url value='/invoice/view/${invoice.id}' />">wyświetl</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<a href="<c:url value='/invoice/add'/>"
						class="btn btn-info pull-right">Wystaw nową fakturę</a>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>