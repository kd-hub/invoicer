<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Faktury <small>Dodaj nową fakturę</small>
					</h1>
				</section>
				<section class="content">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Nowa faktura</h3>
						</div>
						<c:choose>
							<c:when test="${noCustomerError == true || noProductError  == true}">
								<div class="box-body">
									<div class="alert alert-warning">
										<h4>
											<i class="icon fa fa-warning"></i> Nie można dodać faktury.
										</h4>
										<c:if test="${noCustomerError}">
											<p>
												Nie ma żadnego klienta. <a href="<c:url value='/customer/add'/>">Dodaj nowego klienta.</a>
											</p>
										</c:if>
										<c:if test="${noProductError}">
											<p>
												Nie ma żadnego produktu. <a href="<c:url value='/product/add'/>">Dodaj nowy produkt.</a>
											</p>
										</c:if>
									</div>
									<div class="box-footer">
										<a href="<c:url value='/invoice/list'/>" class="btn btn-default pull-right">Anuluj</a>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<form:form modelAttribute="newInvoice" class="form-horizontal">
									<div class="box-body">
										<form:errors path="*" cssClass="alert alert-danger"
											element="div" />
										<div class="form-group">
											<label class="col-sm-2 control-label" for="customer">Klient</label>
											<div class=col-sm-9>
												<form:select path="customer">
													<form:options items="${customersList}" />
												</form:select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="dateOfSale">Data
												sprzedaży</label>
											<div class="col-sm-9">
												<form:input id="dateOfSale" path="dateOfSale" type="text"
													readonly="true" />
												<form:errors path="dateOfSale" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="dateOfIssue">Data
												wystawienia</label>
											<div class=col-sm-9>
												<form:input id="dateOfIssue" path="dateOfIssue" type="text"
													readonly="true" />
												<form:errors path="dateOfIssue" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="paymentDueDate">Termin
												płatności</label>
											<div class=col-sm-9>
												<form:input id="paymentDueDate" path="paymentDueDate"
													type="text" readonly="true" />
												<form:errors path="paymentDueDate" cssClass="text-danger" />
											</div>
										</div>
									</div>

									<div class="box-footer">
										<a href="<c:url value='/invoice/list'/>" class="btn btn-default">Anuluj</a>
										<button type="submit" class="btn btn-info pull-right">Dalej</button>
									</div>
								</form:form>
							</c:otherwise>
						</c:choose>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			$(document).ready(function() {
				$.datepicker.setDefaults($.extend({
					'dateFormat' : 'dd-mm-yy'
				}));
				$(function() {
					$("#dateOfSale").datepicker();
					$("#dateOfIssue").datepicker();
					$("#paymentDueDate").datepicker();
				});
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>