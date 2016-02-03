<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Stawki VAT <small>Lista stawek VAT</small>
					</h1>
				</section>
				<section class="content">
					<c:if test="${!empty vatRateList}">
						<table class="table table-striped table-hover">
							<tr>
								<th>Stawka VAT</th>
								<th>Usuń</th>
							</tr>
							<c:forEach items="${vatRateList}" var="vatrate">
								<tr>
									<td>${vatrate.vatRate}</td>
									<td><a
										href="<c:url value='/vatrate/remove/${vatrate.id}' />">usuń</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<a href="<c:url value='/vatrate/add'/>"
						class="btn btn-info pull-right">Dodaj nową stawkę VAT</a>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>