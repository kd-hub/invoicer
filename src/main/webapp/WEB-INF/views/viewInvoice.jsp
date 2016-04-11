<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Faktury <small>Podgląd faktury</small>
					</h1>
				</section>
				<section class="invoice">
					<c:if test="${invoiceAddSuccess}">
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							<h4>
								<i class="icon fa fa-check"></i> Sukces!
							</h4>
							Faktura nr ${invoice.id} została dodana prawidłowo.
						</div>
					</c:if>
					<div class="row">
						<div class="col-xs-12">
							<h2 class="page-header">
								Faktura VAT nr ${invoice.id}<small class="pull-right">Data
									wystawienia: <fmt:formatDate value="${invoice.dateOfIssue}"
										pattern="dd-MM-yyyy" />
								</small>
							</h2>
						</div>
					</div>
					<div class="row invoice-info">
						<div class="col-sm-4 invoice-col">
							Sprzedawca:
							<address>
								<strong>Hurtownia Spożywcza</strong><br>
								Lwowska 34<br>
								31-712 Kraków<br>
								NIP: 642-864-73-41<br>
							</address>
						</div>
						<div class="col-sm-5 invoice-col">
							Nabywca:
							<address>
								<strong>${invoice.customer.companyName}</strong><br>
								${invoice.customer.streetName} ${invoice.customer.streetNumber}
								${invoice.customer.apartmentNumber}<br>
								${invoice.customer.postalCode} ${invoice.customer.cityName}<br>
								NIP: ${invoice.customer.vatIdNumber}<br>
							</address>
						</div>
						<div class="col-sm-3 invoice-col">
							<b>Data sprzedaży:</b><br>
							<fmt:formatDate value="${invoice.dateOfSale}"
								pattern="dd-MM-yyyy" />
							<br> <b>Termin płatności:</b><br>
							<fmt:formatDate value="${invoice.paymentDueDate}"
								pattern="dd-MM-yyyy" />
						</div>
					</div>

					<!-- Table row -->
					<div class="row">
						<div class="col-xs-12 table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Nazwa artykułu</th>
										<th>Ilość</th>
										<th>Cena jedn. netto</th>
										<th>Wartość netto</th>
										<th>Stawka VAT</th>
										<th>Kwota VAT</th>
										<th>Wartość brutto</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${invoice.invoiceLines}" var="line">
										<tr>
											<td>${line.product.productName}</td>
											<td>${line.quantity}</td>
											<td><fmt:formatNumber value="${line.price}"
													minFractionDigits="0" maxFractionDigits="2" /></td>
											<td><fmt:formatNumber value="${line.netValue}"
													minFractionDigits="0" maxFractionDigits="2" /></td>
											<td>${line.vatRate}%</td>
											<td><fmt:formatNumber value="${line.vatAmount}"
													minFractionDigits="0" maxFractionDigits="2" /></td>
											<td><fmt:formatNumber value="${line.grossValue}"
													minFractionDigits="0" maxFractionDigits="2" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-7"></div>
						<div class="col-xs-5">
							<p class="lead">Razem</p>

							<div class="table-responsive">
								<table class="table">
									<tr>
										<th style="width: 60%">Wartość netto</th>
										<td><fmt:formatNumber value="${invoice.totalAmountNet}"
												minFractionDigits="2" maxFractionDigits="2" /></td>
									</tr>
									<tr>
										<th>Podatek VAT</th>
										<td><fmt:formatNumber
												value="${invoice.totalAmountGross - invoice.totalAmountNet}"
												minFractionDigits="2" maxFractionDigits="2" /></td>
									</tr>
									<tr>
										<th>Wartość bruto</th>
										<td><fmt:formatNumber value="${invoice.totalAmountGross}"
												minFractionDigits="2" maxFractionDigits="2" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="row no-print">
						<div class="col-xs-12">
							<a href="<c:url value='/invoice/list'/>" class="btn btn-default">Powrót</a>
						</div>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>