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
						Faktury <small>Dodaj nową fakturę</small>
					</h1>
				</section>
				<section class="invoice">
					<c:if test="${invoiceAddErrors}">
						<div class="alert alert-danger alert-dismissible">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							<h4>
								<i class="icon fa fa-ban"></i> Błąd!
							</h4>
							Faktura nie została dodana z powodu przekroczenia
							stanów magazynowych.
						</div>
					</c:if>
					<div class="row">
						<div class="col-xs-12">
							<h2 class="page-header">
								Faktura VAT<small class="pull-right">Data wystawienia: <fmt:formatDate
										value="${newInvoice.dateOfIssue}" pattern="dd-MM-yyyy" />
								</small>
							</h2>
						</div>
					</div>
					<div class="row invoice-info">
						<div class="col-sm-8 invoice-col">
							Nabywca:
							<address>
								<strong>${newInvoice.customer.companyName}</strong><br>
								${newInvoice.customer.streetName}
								${newInvoice.customer.streetNumber}
								${newInvoice.customer.apartmentNumber}<br>
								${newInvoice.customer.postalCode}
								${newInvoice.customer.cityName}<br> NIP:
								${newInvoice.customer.vatIdNumber}<br>
							</address>
						</div>
						<div class="col-sm-4 invoice-col">
							<b>Data sprzedaży:</b><br>
							<fmt:formatDate value="${newInvoice.dateOfSale}"
								pattern="dd-MM-yyyy" />
							<br> <b>Termin płatności:</b><br>
							<fmt:formatDate value="${newInvoice.paymentDueDate}"
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
									<c:forEach items="${newInvoice.invoiceLines}" var="line">
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
						<!-- add new product column -->
						<div class="col-xs-8">
							<p class="lead">
								<b>Dodaj nową pozycję:</b>
							</p>
							<form:form modelAttribute="newInvoice" class="form-vertical">
								<form:errors path="*" cssClass="alert alert-danger"
									element="div" />
								<div class="box-body">
									<div class="form-group">
										<label class="control-label" for="selectedProduct">Nazwa
											produktu</label>
										<div>
											<form:select path="selectedProduct">
												<form:options items="${productsList}" />
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label"
											for="selectedProduct.itemSellingPrice">Cena jedn.
											netto</label>
										<div class=>
											<form:input id="selectedProduct.itemSellingPrice"
												path="selectedProduct.itemSellingPrice" type="text"
												class="form-control" style="width: 30%" />
											<form:errors path="selectedProduct.itemSellingPrice"
												cssClass="text-danger" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label"
											for="selectedProduct.itemSellingQuantity">Ilość</label>
										<div class=>
											<form:input id="selectedProduct.itemSellingQuantity"
												path="selectedProduct.itemSellingQuantity" type="text"
												class="form-control" style="width: 30%" />
											<form:errors path="selectedProduct.itemSellingQuantity"
												cssClass="text-danger" />
										</div>
									</div>
									<button type="submit" class="btn btn-info">Dodaj</button>
								</div>

							</form:form>
						</div>
						<!-- /.col -->
						<div class="col-xs-4">
							<p class="lead">Razem</p>

							<div class="table-responsive">
								<table class="table">
									<tr>
										<th style="width: 60%">Wartość netto</th>
										<td><fmt:formatNumber
												value="${newInvoice.totalAmountNet}" minFractionDigits="2"
												maxFractionDigits="2" /></td>
									</tr>
									<tr>
										<th>Podatek VAT</th>
										<td><fmt:formatNumber
												value="${newInvoice.totalAmountGross - newInvoice.totalAmountNet}"
												minFractionDigits="2" maxFractionDigits="2" /></td>
									</tr>
									<tr>
										<th>Wartość bruto</th>
										<td><fmt:formatNumber
												value="${newInvoice.totalAmountGross}" minFractionDigits="2"
												maxFractionDigits="2" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="row no-print">
						<div class="col-xs-12">
							<a href="" class="btn btn-default">Anuluj</a>
							<c:if test="${!empty newInvoice.invoiceLines}">
								<form:form modelAttribute="newInvoice"
									action="${pageContext.request.contextPath}/invoice/add/comlete">
									<button type="submit" class="btn btn-success pull-right">
										Wystaw fakturę</button>
								</form:form>
							</c:if>
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