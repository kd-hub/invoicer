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
						Produkty <small>Dodaj nowy produkt</small>
					</h1>
				</section>
				<section class="content">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Nowy produkt</h3>
						</div>
						<c:choose>
							<c:when test="${empty vatRatesList}">
								<div class="box-body">
									<div class="alert alert-warning">
										<h4>
											<i class="icon fa fa-warning"></i> Błąd!
										</h4>
										Nie można dodać nowego produktu, ponieważ nie ma żadnej stawki
										VAT. <a href="<c:url value='/vatrate/add'/>">Dodaj nową
											stawkę VAT.</a>
									</div>
									<div class="box-footer">
										<a href="<c:url value='/product/list'/>"
											class="btn btn-default">Anuluj</a> <a
											href="<c:url value='/vatrate/add'/>"
											class="btn btn-info pull-right">Dodaj nową stawkę VAT</a>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<form:form modelAttribute="newProduct" class="form-horizontal">
									<div class="box-body">
										<form:errors path="*" cssClass="alert alert-danger"
											element="div" />
										<div class="form-group">
											<label class="col-sm-2 control-label" for="productName">Nazwa
												produktu</label>
											<div class=col-sm-9>
												<form:input id="productName" path="productName" type="text"
													class="form-control" />
												<form:errors path="productName" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="purchasePrice">Cena
												nabycia</label>
											<div class=col-sm-9>
												<form:input id="purchasePrice" path="purchasePrice"
													type="text" class="form-control" />
												<form:errors path="purchasePrice" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="stockQuantity">Ilość
												w magazynie</label>
											<div class=col-sm-9>
												<form:input id="stockQuantity" path="stockQuantity"
													type="text" class="form-control" />
												<form:errors path="stockQuantity" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="vatRate">Stawka
												VAT</label>
											<div class=col-sm-9>
												<form:select path="vatRate">
													<form:options items="${vatRatesList}" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="box-footer">
										<a href="<c:url value='/product/list'/>"
											class="btn btn-default">Anuluj</a>
										<button type="submit" class="btn btn-info pull-right">Dodaj</button>
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
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>