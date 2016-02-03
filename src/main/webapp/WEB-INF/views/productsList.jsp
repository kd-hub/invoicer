<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Produkty <small>Lista produktów</small>
					</h1>
				</section>
				<section class="content">
					<c:if test="${!empty productsList}">
						<table class="table table-striped table-hover">
							<tr>
								<th>Nazwa produktu</th>
								<th>Cena zakupu</th>
								<th>Cena sprzedaży</th>
								<th>Ilość w magazynie</th>
								<th>Stawka VAT</th>
								<th>Edytuj</th>
								<th>Usuń</th>
							</tr>
							<c:forEach items="${productsList}" var="product">
								<tr>
									<td>${product.productName}</td>
									<td>${product.purchasePrice}</td>
									<td>${product.sellingPrice}</td>
									<td>${product.stockQuantity}</td>
									<td>${product.vatRate.vatRate}%</td>
									<td><a
										href="<c:url value='/product/edit/${product.id}' />">edytuj</a></td>
									<td><a
										href="<c:url value='/product/remove/${product.id}' />">usuń</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<a href="<c:url value='/product/add'/>"
						class="btn btn-info pull-right">Dodaj nowy produkt</a>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>